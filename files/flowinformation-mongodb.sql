db.createCollection( "flowinformation", {
    validator: { $jsonSchema: {
            bsonType: "object",
            properties: {
                applicationName: {
                    bsonType: "string",
                    description: "Application Name"
                },
                applicationVersion: {
                    bsonType : "string",
                    description: "Application Version"
                },
                applicationInstanceIp: {
                    bsonType : "string",
                    description: "Application Instance IP"
                },
                startTime: {
                    bsonType : "string",
                    description: "Event Start Time"
                },
                endTime: {
                    bsonType : "string",
                    description: "Event End Time"
                },
                url: {
                    bsonType : "string",
                    description: "Event URL"
                },
                requestType: {
                    bsonType : "string",
                    description: "Request Type : POST, GET etc.."
                },
                className: {
                    bsonType : "string",
                    description: "Event Class Name "
                },
                methodName: {
                    bsonType : "string",
                    description: "Event Method Name ex: org.cev-her.spark"
                },
                userLoginName: {
                    bsonType : "string",
                    description: "User Login Name"
                },
                userSessionId: {
                    bsonType : "string",
                    description: "User common Session ID"
                },
                exitCode: {
                    bsonType : "string",
                    description: "Event Exit Code ex: SUCCESS, EXCEPTION etc.."
                },
                exceptionType: {
                    bsonType : "string",
                    description: ""
                },
                exceptionMessage: {
                    bsonType : "string",
                    description: ""
                },
                exceptionStackTrace: {
                    bsonType : "string",
                    description: ""
                },
                externalData: {
                    bsonType : "string",
                    description: ""
                },
                inParams: {
                    bsonType : "string",
                    description: ""
                },
                outParams: {
                    bsonType : "string",
                    description: ""
                }
            }
        } }
} )