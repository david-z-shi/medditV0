# Initial Setup / Reference
- Uses react, typescript, and node/npm
- Used create-react-app to do setup on codesandbox
- fhirclient package
  - This is the npm package provided by SMART on FHIR
- if using this package directly run `npm install` after unzipping

# Step 1 - Get your client connected
- Get a client ID to connect to open.epic servers
  - sign up on open.epic.com and Create an app!
- import the oauth module from fhirclient
- in your index page onLoad call the oauth init module with the required configuration to get an authorization token
- redirect back to the same page and it will continue on

# Step 2 - Request some data
- use the `client` object obtained from the init function in Step 1
- the `patient` property contains the patient you logged in as via oauth2 and now have access to 
- use `client.patient.read()` to get the patient object.
- use `client.request(...)` to get another resource

# Step 3 - Keep Building
That's it! Now use the data in your application. Hold on to the client object to request more data as you go.

