/// <reference path="../node_modules/fhirclient/lib/types.d.ts"/>

import * as React from "react";
import { render } from "react-dom";
import { oauth2 as SMART} from "fhirclient";
import App from "./App";

const rootElement: HTMLElement | null = document.getElementById("root");


async function main(): Promise<void> {
  try {

    const client: fhirclient.Client = await SMART.init({
      iss:"https://open-ic.epic.com/FHIR/api/FHIR/DSTU2",   // url of organization you're connecting to
      clientId: "0600075f-a763-49a9-9a41-932261874363",     // client identifier used to identify your application
      scope: "patient/*.read",                              // requested permissions
      // tslint:disable-next-line: max-line-length
      redirectUri: "./index.html",                          // where you want to redirect to upon successful login (we're going to come right back to this page)
    }); 

    const [patient, allergies, meds]: fhirclient.JsonObject[] = await Promise.all([
      client.patient.read(),                                                // get the patient object
      client.request(`/AllergyIntolerance?patient=${client.patient.id}`, {  // get the allergy object
        pageLimit: 0,
        flat: true
      }),
      client.request(`/MedicationOrder?patient=${client.patient.id}`, {     // get the list of patient medications
        resolveReferences: "medicationReference",
        pageLimit: 0,
        flat: true
      }),
      client.request(`/Condition?patient=${client.patient.id}`, {     // get the list of condition medications 
        pageLimit: 0 ,
        flat: true
      })]);


    // show our application!
    render(<App patient={patient} allergies={allergies} medications={meds} />, rootElement);

  // helpful top level error handler to show a stack in the console and browser
  } catch (error) {

    console.error(error);
    render(
      <>
        <br />
        <pre>{error.stack}</pre>
      </>,
      rootElement
    );
  }
  
}

main();