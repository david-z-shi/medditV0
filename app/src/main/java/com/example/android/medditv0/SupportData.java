//package com.example.android.medditv0;
//
//public class SupportData {
//
//    public String getDatabaseName() {
//        return "meddit";
//    }
//
//    public String getApiKey() {
//        return "";
//    }
//    public String getBaseUrl() {
//        return "https://api.mlab.com/api/1/databases/"+getDatabaseName()+"/collections/";
//    }
//
//    public String apiKeyUrl() {
//        return "?apiKey="+getApiKey();
//    }
//    public String collectionName() {
//        return "Contacts";
//    }
//    public String buildContactsSaveURL() {
//        return getBaseUrl()+collectionName()+apiKeyUrl();
//    }
//    public String buildContactsFetchURL() {
//        return getBaseUrl()+collectionName()+apiKeyUrl();
//    }
//    public String createUser(User user) {
//        return String.format("{\"first_name\": \"%s\", "+ "\"last_name\": \"%s\", " + "\"phone\": \"%s\"}", contact.getFirst_name(), contact.getLast_name(), contact.getPhone_nubmer());
//    }
//}
