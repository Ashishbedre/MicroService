Endpoints1:
	versionControl:
		GET http://localhost:8081/v1/versionControl/checkForUpdate(save and get the data)
	responce:[	
 
    {
        "id": 1,
        "product": "sdn-controller-mini",
        "version": "v1"
    },
    {
        "id": 2,
        "product": "niralos-5g-core",
        "version": "gnb_history_testing"
    }]
____________________________________________________________________________________________________________________________________________________________
	versionControl:
		GET http://localhost:8081/v1/versionControl/getProductVersion(get the data)
	responce:[
    {
        "id": 1,
        "product": "sdn-controller-mini",
        "version": "v1"
    },
    {
        "id": 2,
        "product": "niralos-5g-core",
        "version": "gnb_history_testing"
    }]


------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

Endpoints2:
	globalSDN:
		POST http://localhost:8081/v1/globalSDN/SiteManagement/getUpgradeVersion
	body:[
 
  {
    "repo": "niralos-5g-core",
    "tag": "v-2.2.1"
  },
  {
    "repo": "niralos-edge-agent",
    "tag": "kafka-v1"
  }
]
	responce:
		[
    {
        "product": "niralos-5g-core",
        "versions": [
	
            {
                "version": "gnb_history_testing"
            },
            {
                "version": "v-2.2.1_log-fix"
            }
        ]
    },
    {
        "product": "niralos-edge-agent",
        "versions": [
            {
                "version": "spice-server-v1"
            },
            {
                "version": "spice-test"
            },
            {
                "version": "test"
            }
        ]
    }
]
____________________________________________________________________________________________________________________________________________________________
	globalSDN:
		post http://localhost:8081/v1/globalSDN/SiteManagement/getDowngradeVersion
	body:
		[
  {
    "repo": "niralos-5g-core",
    "tag": "v-2.2.1"
  },
  {
    "repo": "niralos-edge-agent",
    "tag": "kafka-v1"
  }
]
	responce:
		[
    {
        "product": "niralos-5g-core",
        "versions": [
	
            {
                "version": "tls-testing"
            },
            {
                "version": "hfcl-v_2.1.1-d"
            },
            {
                "version": "aio-2.1-d"
            },
            {
                "version": "aio-2.2"
            },
            {
                "version": "aio-2.1"
            }
        ]
    },
    {
        "product": "niralos-edge-agent",
        "versions": [
            {
                "version": "v2.2-kafka"
            },
            {
                "version": "aio-2.2"
            }
        ]
    }
]

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
Endpoints3:
	Release_management:
		GET http://localhost:8081/v1/Release_management/productList
	responce:[
 
    "NiralOS 5g Core",
    "Niralos SDN Controller"
]
___________________________________________________________________________________________________________________________________________________________
	Release_management:
		GET http://localhost:8081/v1/Release_management/releaseVersionList/NiralOS 5g Core
	responce:[
    {
        "version": "V1",
        "changeLog": "Sample Change Log",
        "knownFix": "Sample Know Fix",
        "download": 100
    }
]
____________________________________________________________________________________________________________________________________________________________
	Release_management:
		Delete http://localhost:8081/v1/Release_management/deleteReleaseVersion/NiralOS 5g Core/1
	responce:Done

------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

		

	
