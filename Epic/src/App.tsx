/// <reference path="../node_modules/fhirclient/lib/types.d.ts"/>

import * as React from "react";
import { getPath } from "fhirclient/lib/lib";

const rxnorm = "http://www.nlm.nih.gov/research/umls/rxnorm";

function getMedicationName(medCodings: any[] = []): string {
    let out: string = "Unnamed Medication(TM)";
    const coding: any = medCodings.find(c => c.system === rxnorm);
    if (coding && coding.display) {
        out = coding.display;
    }
    return out;
}

function mapAllergyCriticality(critCode) {
    switch(critCode) {
        case "CRITL": 
            return "Low Risk";
        case "CRITH":
            return "High Risk";
        case "CRITU":
            return "Unable to determine";
    }
    return "-";
}

function parseAllergyReaction(reactions = []) {
    const manifestationsArrays = reactions.map(reaction => reaction.manifestation);
    const manifestations = manifestationsArrays.reduce((previous, current) => previous.concat(current));
    return manifestations.map(manifestation => manifestation.text).toString();
}

function PatientName({ name = [] }: { name: any[]}): JSX.Element {
    const entry: any = name.find(nameRecord => nameRecord.use === "official") || name[0];
    if (!entry) {
        return <h1>No Name</h1>;
    }
    return <h1>{entry.given.join(" ") + " " + entry.family}</h1>;
}

function PatientBanner(patient: fhirclient.JsonObject): JSX.Element {
    return (
        <div>
            <PatientName name={patient.name} />
            <span>
                Gender: <b>{patient.gender}</b>,{" "}
            </span>
            <span>
                DOB: <b>{patient.birthDate}</b>
            </span>
        </div>
    );
}

function MedRow({ med }: { med: fhirclient.JsonObject }): JSX.Element {
    const name: string = getMedicationName(
        getPath(med, "medicationCodeableConcept.coding") ||
            getPath(med, "medicationReference.code.coding")
    );
    return (
        <tr>
            <td>
                <b>{name}</b>
            </td>
            <td>{med.status || "-"}</td>
            <td>{med.intent || "-"}</td>
            <td>{getPath(med, "dosageInstruction.0.text") || "-"}</td>
        </tr>
    );
}

function AllergyRow({ allergy }) {
    const name = getPath(allergy, "substance.text");
    const status = getPath(allergy,"status");
    const crit = mapAllergyCriticality(getPath(allergy,"criticality"));
    const reaction = parseAllergyReaction(getPath(allergy,"reaction"));
    return (
        <tr>
            <td>
                <b>{name}</b>
            </td>
            <td>{status || "-"}</td>
            <td>{crit || "-"}</td>
            <td>{reaction || "-"}</td>
        </tr>
    );
}

interface IAppParams {
    patient: fhirclient.JsonObject;
    allergies: fhirclient.JsonObject;
    medications: fhirclient.JsonObject;
}

function App({ patient, allergies, medications }: IAppParams ): JSX.Element {
    return (
        <div className="App">
            <PatientBanner {...patient} />
            <hr />
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th>Allergy</th>
                        <th>Status</th>
                        <th>Criticality</th>
                        <th>Reaction</th>
                    </tr>
                </thead>
                <tbody>
                    {allergies.map(allergy => (
                        <AllergyRow key={allergy.id} allergy={allergy} />
                    ))}
                </tbody>
            </table>
            <br/><br/>
            <table className="table table-hover">
                <thead>
                    <tr>
                        <th>Medication</th>
                        <th>Status</th>
                        <th>Intent</th>
                        <th>Dosage Instruction</th>
                    </tr>
                </thead>
                <tbody>
                    {medications.map(medication => (
                        <MedRow key={medication.id} med={medication} />
                    ))}
                </tbody>
            </table>
            {/* <pre>{ JSON.stringify(meds, null, 4) }</pre> */}
        </div>
    );
}

export default App;
