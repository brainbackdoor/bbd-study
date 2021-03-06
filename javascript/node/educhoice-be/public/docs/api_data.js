define({ "api": [
  {
    "type": "delete",
    "url": "/api/academy/:id",
    "title": "Delete Academy",
    "version": "0.1.0",
    "name": "DeleteAcademy",
    "group": "Academy",
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 4\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/academy.js",
    "groupTitle": "Academy"
  },
  {
    "type": "get",
    "url": "/api/academy",
    "title": "Get Academy list",
    "version": "0.1.0",
    "name": "GetAcademies",
    "group": "Academy",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Address",
            "optional": false,
            "field": "address",
            "description": "<p>Address of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "accountId",
            "description": "<p>ID of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "academyName",
            "description": "<p>Name of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "ownerName",
            "description": "<p>Name of the owner.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "academyPhoneNumber",
            "description": "<p>PhoneNumber of the academy.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "  HTTP/1.1 200 OK\n{\n    \"address\": {\n        \"dong\": \"상계동\",\n        \"sido\": \"서울\",\n        \"sigungu\": \"노원구\",\n        \"fullAddress\": \"서울 노원구 상계동 455 백산빌딩\",\n        \"roadAddress\": \"서울 노원구 한글비석로 460\",\n        \"latitude\": 127.06697859544342,\n        \"longitude\": 37.66444002512082\n    },\n    \"created\": \"2018-03-09T07:34:39.629Z\",\n    \"_id\": \"5aa2390fa26a0f7a8c5bbabd\",\n    \"accountId\": \"bbd@educhoice.com\",\n    \"academyName\": \"모두의학원\",\n    \"ownerName\": \"이준\",\n    \"academyPhoneNumber\": \"07012345678\",\n    \"__v\": 0\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/academy.js",
    "groupTitle": "Academy"
  },
  {
    "type": "get",
    "url": "/api/academy/:id",
    "title": "Get Academy inpormation",
    "version": "0.1.0",
    "name": "GetAcademy",
    "group": "Academy",
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Address",
            "optional": false,
            "field": "address",
            "description": "<p>Address of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "Account",
            "optional": false,
            "field": "corporateAccount",
            "description": "<p>Account of the corporate.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "academyName",
            "description": "<p>Name of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "academyPhoneNumber",
            "description": "<p>PhoneNumber of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "Course",
            "optional": false,
            "field": "courses",
            "description": "<p>Course of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "Event",
            "optional": false,
            "field": "events",
            "description": "<p>Event of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "HashTag",
            "optional": false,
            "field": "hashTags",
            "description": "<p>HashTag of the academy.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n   \"address\": {\n       \"dong\": \"상계동\",\n       \"sido\": \"서울\",\n       \"sigungu\": \"노원구\",\n       \"fullAddress\": \"서울 노원구 상계동 455 백산빌딩\",\n       \"roadAddress\": \"서울 노원구 한글비석로 460\",\n       \"latitude\": 127.06697859544342,\n       \"longitude\": 37.66444002512082\n   },\n   \"corporateAccount\": {\n       \"phoneNo\": \"07012345678\",\n       \"accountName\": \"이준\"\n   },\n   \"academyName\": \"모두의학원\",\n   \"academyPhoneNumber\": \"07012345678\",\n   \"courses\": [\n       {\n           \"dayOfWeek\": [\n               {\n                   \"startTime\": \"18:00\",\n                   \"endTime\": \"20:00\",\n                   \"day\": \"월\",\n                   \"_id\": \"5aa2246a3e230146f31adc00\"\n               },\n               {\n                   \"startTime\": \"18:00\",\n                   \"endTime\": \"20:00\",\n                   \"day\": \"화\",\n                   \"_id\": \"5aa2246a3e230146f31adbff\"\n               }\n           ],\n           \"_id\": \"5aa222342ae1501ddf695bf7\",\n           \"courseType\": \"special\",\n           \"accountId\": \"bbd@educhoice.com\",\n           \"coursesClassification\": \"물리\",\n           \"subjectClassification\": \"물리\",\n           \"courseName\": \"물리(과탐)-고2\",\n           \"grade\": \"고등2\",\n           \"tuition\": 400000,\n           \"duration\": \"2018.01.01 ~ 2018.01.03\"\n       }\n   ],\n   \"events\": [\n       {\n           \"_id\": \"5aa22998ec1be63d3c76bb19\",\n           \"title\": \"이벤트타이틀\",\n           \"content\": \"컨텐츠수\"\n       }\n   ],\n   \"hashTags\": [\n       {\n           \"_id\": \"5aa23c3d6d0e51514fb618e8\",\n           \"title\": \"태그\"\n       },\n       {\n           \"_id\": \"5aa248f9fd158d2be65387ea\",\n           \"title\": \"태그\"\n       }\n   ],\n   \"created\": \"2018-03-09T19:39:20.192Z\",\n   \"_id\": \"5aa2e2e8e7d46c44ccf24588\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 2\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/academy.js",
    "groupTitle": "Academy"
  },
  {
    "type": "post",
    "url": "/api/academy",
    "title": "Post Academy list",
    "version": "0.1.0",
    "name": "PostAcademy",
    "group": "Academy",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Time",
            "optional": false,
            "field": "time",
            "description": "<p>시간 조건  {&quot;day&quot;:[],&quot;startTime&quot;:&quot;09:00&quot;,&quot;endTime&quot;:&quot;22:00&quot;}</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "grade",
            "description": "<p>학년</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "subject",
            "description": "<p>과목</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>주소</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "latitude",
            "description": "<p>위도</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "longitude",
            "description": "<p>경도</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "carAvailable",
            "description": "<p>차량운행여부</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "address",
            "description": "<p>검색 지역명</p>"
          },
          {
            "group": "Success 200",
            "type": "Boolean",
            "optional": false,
            "field": "carAvailable",
            "description": "<p>차량운행여부</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "grade",
            "description": "<p>학년 [개발중]</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "subject",
            "description": "<p>과목 [개발중]</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n      \"address\": {\n          \"dong\": \"상계동\",\n          \"sido\": \"서울\",\n          \"sigungu\": \"노원구\",\n          \"fullAddress\": \"서울 노원구 상계동 455 백산빌딩\",\n          \"roadAddress\": \"서울 노원구 한글비석로 460\",\n          \"latitude\": 127.06697859544342,\n          \"longitude\": 37.66444002512082\n      },\n      \"subjects\": [\n          \"사회\"\n      ],\n      \"grades\": [\n          \"고등3\"\n      ],\n      \"courses\": [\n          {\n              \"date\": {\n                  \"created\": \"2018-03-11T19:02:38.147Z\",\n                  \"edited\": \"2018-03-11T19:02:38.147Z\"\n              },\n              \"dayOfWeek\": [\n                  {\n                      \"startTime\": \"16:00\",\n                      \"endTime\": \"18:00\",\n                      \"day\": \"월\",\n                      \"_id\": \"5aa57d4ec08563172f44dca8\"\n                  }\n              ],\n              \"is_edited\": false,\n              \"courseType\": \"normal\",\n              \"coursesClassification\": \"사회\",\n              \"subjectClassification\": \"한국사\",\n              \"courseName\": \"한국사 뽀개기\",\n              \"grade\": \"고등3\",\n              \"tuition\": 400000,\n              \"_id\": \"5aa57d4ec08563172f44dca9\"\n          }\n      ],\n      \"events\": [],\n      \"hashTags\": [],\n      \"created\": \"2018-03-11T18:30:13.171Z\",\n      \"_id\": \"5aa575b5807c31f359bfbd69\",\n      \"accountId\": \"5aa575b5807c31f359bfbd68\",\n      \"academyName\": \"모두의학원\",\n      \"ownerName\": \"이준\",\n      \"academyPhoneNumber\": \"07012345678\",\n      \"carAvailable\": true,\n      \"__v\": 4\n  }",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/academy.js",
    "groupTitle": "Academy"
  },
  {
    "type": "put",
    "url": "/api/academy/:id",
    "title": "PUT Academy inpormation",
    "version": "0.1.0",
    "name": "PutAcademy",
    "group": "Academy",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "academyName",
            "description": "<p>학원이름</p>"
          },
          {
            "group": "Parameter",
            "type": "Address",
            "optional": false,
            "field": "address",
            "description": "<p>학원주소</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "carAvaiable",
            "description": "<p>차량운행여부</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "inquiryResponseRate",
            "description": "<p>응답률</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "introduction",
            "description": "<p>소개</p>"
          }
        ]
      }
    },
    "success": {
      "fields": {
        "Success 200": [
          {
            "group": "Success 200",
            "type": "Address",
            "optional": false,
            "field": "address",
            "description": "<p>Address of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "Account",
            "optional": false,
            "field": "corporateAccount",
            "description": "<p>Account of the corporate.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "academyName",
            "description": "<p>Name of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "String",
            "optional": false,
            "field": "academyPhoneNumber",
            "description": "<p>PhoneNumber of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "Course",
            "optional": false,
            "field": "courses",
            "description": "<p>Course of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "Event",
            "optional": false,
            "field": "events",
            "description": "<p>Event of the academy.</p>"
          },
          {
            "group": "Success 200",
            "type": "HashTag",
            "optional": false,
            "field": "hashTags",
            "description": "<p>HashTag of the academy.</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n   \"address\": {\n       \"dong\": \"상계동\",\n       \"sido\": \"서울\",\n       \"sigungu\": \"노원구\",\n       \"fullAddress\": \"서울 노원구 상계동 455 백산빌딩\",\n       \"roadAddress\": \"서울 노원구 한글비석로 460\",\n       \"latitude\": 127.06697859544342,\n       \"longitude\": 37.66444002512082\n   },\n   \"corporateAccount\": {\n       \"phoneNo\": \"07012345678\",\n       \"accountName\": \"이준\"\n   },\n   \"academyName\": \"모두의학원\",\n   \"academyPhoneNumber\": \"07012345678\",\n   \"courses\": [\n       {\n           \"dayOfWeek\": [\n               {\n                   \"startTime\": \"18:00\",\n                   \"endTime\": \"20:00\",\n                   \"day\": \"월\",\n                   \"_id\": \"5aa2246a3e230146f31adc00\"\n               },\n               {\n                   \"startTime\": \"18:00\",\n                   \"endTime\": \"20:00\",\n                   \"day\": \"화\",\n                   \"_id\": \"5aa2246a3e230146f31adbff\"\n               }\n           ],\n           \"_id\": \"5aa222342ae1501ddf695bf7\",\n           \"courseType\": \"special\",\n           \"accountId\": \"bbd@educhoice.com\",\n           \"coursesClassification\": \"물리\",\n           \"subjectClassification\": \"물리\",\n           \"courseName\": \"물리(과탐)-고2\",\n           \"grade\": \"고등2\",\n           \"tuition\": 400000,\n           \"duration\": \"2018.01.01 ~ 2018.01.03\"\n       }\n   ],\n   \"events\": [\n       {\n           \"_id\": \"5aa22998ec1be63d3c76bb19\",\n           \"title\": \"이벤트타이틀\",\n           \"content\": \"컨텐츠수\"\n       }\n   ],\n   \"hashTags\": [\n       {\n           \"_id\": \"5aa23c3d6d0e51514fb618e8\",\n           \"title\": \"태그\"\n       },\n       {\n           \"_id\": \"5aa248f9fd158d2be65387ea\",\n           \"title\": \"태그\"\n       }\n   ],\n   \"created\": \"2018-03-09T19:39:20.192Z\",\n   \"_id\": \"5aa2e2e8e7d46c44ccf24588\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 NO RESOURCE\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 4\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 5\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/academy.js",
    "groupTitle": "Academy"
  },
  {
    "type": "get",
    "url": "/api/account/getInfo",
    "title": "Get Session Information",
    "version": "0.1.0",
    "name": "GetLoginInfo",
    "group": "Account",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n   \"info\": {\n       \"_id\": \"5aa2ea5ce7d46c44ccf24589\",\n       \"loginId\": \"bbd@modoohakwon.com\",\n       \"name\": \"bbd\",\n       \"type\": \"parents\"\n    }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "LOGIN",
            "description": "<p>FAILED</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 LOGIN FAILED\n{\n  \"code\" : 1\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/account.js",
    "groupTitle": "Account"
  },
  {
    "type": "post",
    "url": "/api/account/signin",
    "title": "Post Signin information",
    "version": "0.1.0",
    "name": "Login",
    "group": "Account",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loginId",
            "description": "<p>유저 ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>비밀번호</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "LOGIN",
            "description": "<p>FAILED</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 401 LOGIN FAILED\n{\n  \"error\": \"LOGIN FAILED\",\n  \"code\" : 1\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/account.js",
    "groupTitle": "Account"
  },
  {
    "type": "post",
    "url": "/api/account/logout",
    "title": "Post Logout Request",
    "version": "0.1.0",
    "name": "Logout",
    "group": "Account",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/account.js",
    "groupTitle": "Account"
  },
  {
    "type": "post",
    "url": "/api/account/signup",
    "title": "Post Signup Information",
    "version": "0.1.0",
    "name": "PostAccountAndAcademy",
    "group": "Account",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "requestType",
            "description": "<p>유저 타입 { parents | corporate }</p>"
          },
          {
            "group": "Parameter",
            "type": "Boolean",
            "optional": false,
            "field": "marketingInfo",
            "description": "<p>마케팅 수신 동의여부</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "loginId",
            "description": "<p>유저 ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "password",
            "description": "<p>비밀번호</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "memberAddress",
            "description": "<p>주소 [학부모일 경우만 기입]</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "nickname",
            "description": "<p>별명 [학부모일 경우만 기입]</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "originalName",
            "description": "<p>가입자이름 [학원일 경우만 기입]</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "phoneNo",
            "description": "<p>가입자번호 [학원일 경우만 기입]</p>"
          },
          {
            "group": "Parameter",
            "type": "Academy",
            "optional": false,
            "field": "academy",
            "description": "<p>학원정보 [학원일 경우만 기입]</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 201 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "BAD",
            "description": "<p>REQUEST TYPE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "LOGIN",
            "description": "<p>ID EXISTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NICKNAME",
            "description": "<p>EXISTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "ACADEMY",
            "description": "<p>EXISTS</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 BAD REQUEST TYPE\n{\n  \"error\": \"BAD REQUEST TYPE\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 BAD PASSWORD\n{\n  \"error\": \"BAD PASSWORD\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 409 LOGIN ID EXISTS\n{\n  \"error\": \"LOGIN ID EXISTS\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 BAD REQUEST DATA\n{\n  \"error\": \"BAD REQUEST DATA\",\n  \"code\" : 4\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 NICKNAME EXISTS\n{\n  \"error\": \"NICKNAME EXISTS\",\n  \"code\" : 5\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 6\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 409 ACADEMY EXISTS\n{\n  \"error\": \"ACADEMY EXISTS\",\n  \"code\" : 7\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/account.js",
    "groupTitle": "Account"
  },
  {
    "type": "delete",
    "url": "/api/answer/:questionId",
    "title": "Delete Answer Information [Dev]",
    "version": "0.1.0",
    "name": "DeleteAnswerInformation",
    "group": "Answer",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 4\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/answer.js",
    "groupTitle": "Answer"
  },
  {
    "type": "get",
    "url": "/api/answer",
    "title": "Get Answer Information [Dev]",
    "version": "0.1.0",
    "name": "GetAnswers",
    "group": "Answer",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "  HTTP/1.1 200 OK\n {\n     \"date\": {\n         \"created\": \"2018-03-09T18:53:46.131Z\",\n         \"edited\": \"2018-03-09T18:53:46.131Z\"\n     },\n     \"is_edited\": false,\n     \"_id\": \"5aa2d83a9981b98349922da0\",\n     \"accountId\": \"bbd@educhoice.com\",\n     \"accountName\": \"모두의학원\",\n     \"questionerId\": \"brainbackdoor@modoohakwon.com\",\n     \"questionId\": \"5aa2bc5eb4b817f1759f0cc2\",\n     \"content\": \"댓글 컨텐츠\",\n   \"__v\": 0\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/answer.js",
    "groupTitle": "Answer"
  },
  {
    "type": "post",
    "url": "/api/answer/:questionId",
    "title": "Post Answer Information",
    "version": "0.1.0",
    "name": "PostAnswerInformation",
    "group": "Answer",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>답글 컨텐츠</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 201 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 3\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/answer.js",
    "groupTitle": "Answer"
  },
  {
    "type": "delete",
    "url": "/api/course/:id",
    "title": "Delete Course Information",
    "version": "0.1.0",
    "name": "DeleteCourseInformation",
    "group": "Course",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 4\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/course.js",
    "groupTitle": "Course"
  },
  {
    "type": "post",
    "url": "/api/course",
    "title": "Post Course information",
    "version": "0.1.0",
    "name": "PostCourse",
    "group": "Course",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "courseType",
            "description": "<p>강좌 유형 { normal | special }</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "coursesClassification",
            "description": "<p>과목 대분류</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "subjectClassification",
            "description": "<p>과목 소분류</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "courseName",
            "description": "<p>과목 이름</p>"
          },
          {
            "group": "Parameter",
            "type": "Number",
            "optional": false,
            "field": "tuition",
            "description": "<p>수업료</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "grade",
            "description": "<p>학년</p>"
          },
          {
            "group": "Parameter",
            "type": "Array",
            "optional": false,
            "field": "dayOfWeek",
            "description": "<p>수업일자</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "duration",
            "description": "<p>수업기간 (특강(courseType:special)에 한함)</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 201 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "BAD",
            "description": "<p>REQUEST TYPE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 BAD REQUEST TYPE\n{\n  \"error\": \"BAD REQUEST TYPE\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 BAD REQUEST DATA\n{\n  \"error\": \"BAD REQUEST DATA\",\n  \"code\" : 4\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/course.js",
    "groupTitle": "Course"
  },
  {
    "type": "put",
    "url": "/api/course/:id",
    "title": "Put Course Information",
    "version": "0.1.0",
    "name": "PutCourseInformation",
    "group": "Course",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "courseType",
            "description": "<p>강좌 유형 { normal | special }</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "coursesClassification",
            "description": "<p>과목 대분류</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "subjectClassification",
            "description": "<p>과목 소분류</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "couorseName",
            "description": "<p>과목 이름</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "grade",
            "description": "<p>학년</p>"
          },
          {
            "group": "Parameter",
            "type": "Array",
            "optional": false,
            "field": "dayOfWeek",
            "description": "<p>수업일자</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "duration",
            "description": "<p>수업기간 (특강(courseType:special)에 한함)</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "BAD",
            "description": "<p>REQUEST TYPE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 4\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 5\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 BAD REQUEST TYPE\n{\n  \"error\": \"BAD REQUEST TYPE\",\n  \"code\" : 6\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 BAD REQUEST DATA\n{\n  \"error\": \"BAD REQUEST DATA\",\n  \"code\" : 7\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/course.js",
    "groupTitle": "Course"
  },
  {
    "type": "delete",
    "url": "/api/event/:id",
    "title": "Delete Event Information",
    "version": "0.1.0",
    "name": "DeleteEventInformation",
    "group": "Event",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 4\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/event.js",
    "groupTitle": "Event"
  },
  {
    "type": "post",
    "url": "/api/event",
    "title": "Post Event information",
    "version": "0.1.0",
    "name": "PostEvent",
    "group": "Event",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>이벤트제목</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>이벤트 컨텐츠</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 201 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/event.js",
    "groupTitle": "Event"
  },
  {
    "type": "put",
    "url": "/api/event/:id",
    "title": "Put Event Information",
    "version": "0.1.0",
    "name": "PutEvent",
    "group": "Event",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>이벤트제목</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>이벤트 컨텐츠</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 4\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 5\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/event.js",
    "groupTitle": "Event"
  },
  {
    "type": "delete",
    "url": "/api/hashTag/:id",
    "title": "Delete HashTag Information",
    "version": "0.1.0",
    "name": "DeleteHashTagInformation",
    "group": "HashTag",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 4\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/hashTag.js",
    "groupTitle": "HashTag"
  },
  {
    "type": "post",
    "url": "/api/hashTag",
    "title": "Post HashTag information",
    "version": "0.1.0",
    "name": "PostHashTag",
    "group": "HashTag",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>해쉬태그</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 201 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/hashTag.js",
    "groupTitle": "HashTag"
  },
  {
    "type": "put",
    "url": "/api/hashTag/:id",
    "title": "Put HashTag Information [Dev]",
    "version": "0.1.0",
    "name": "PutHashTag",
    "group": "HashTag",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "title",
            "description": "<p>해쉬태그</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 4\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 5\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/hashTag.js",
    "groupTitle": "HashTag"
  },
  {
    "type": "delete",
    "url": "/api/question/:id",
    "title": "Delete Question Information [Dev]",
    "version": "0.1.0",
    "name": "DeleteQuestionInformation",
    "group": "Question",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 4\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/question.js",
    "groupTitle": "Question"
  },
  {
    "type": "get",
    "url": "/api/question/node/:questionId",
    "title": "Get Question Information",
    "version": "0.1.0",
    "name": "GetQuestionInformation",
    "group": "Question",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n   \"accountId\": \"bbd@educhoice.com\",\n   \"accountName\": \"모두의학원\",\n   \"answerContent\": \"댓글 컨텐츠\",\n   \"created\": \"2018-03-09T18:53:46.131Z\",\n   \"reply\": [\n       {\n           \"_id\": \"5aa2d8549981b98349922da1\",\n           \"role\": \"corporate\",\n           \"accountName\": \"모두의학원\",\n           \"content\": \"댓글 컨텐츠\"\n       }\n   ],\n   \"_id\": \"5aa3804b9679db694a27b96e\"\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NO RESOURCE \n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 2\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/question.js",
    "groupTitle": "Question"
  },
  {
    "type": "get",
    "url": "/api/question/list",
    "title": "Get Question List",
    "version": "0.1.0",
    "name": "GetQuestionList",
    "group": "Question",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n[\n  {\n      \"date\": {\n          \"created\": \"2018-03-09T16:54:54.481Z\",\n          \"edited\": \"2018-03-09T16:54:54.481Z\"\n      },\n      \"receivers\": [\n          {\n              \"receiverId\": \"bbd@educhoice.com\",\n              \"_id\": \"5aa2bc5eb4b817f1759f0cc1\"\n          },\n          {\n              \"receiverId\": \"bbd2@educhoice.com\",\n              \"_id\": \"5aa2bc5eb4b817f1759f0cc0\"\n          }\n      ],\n      \"is_edited\": false,\n      \"_id\": \"5aa2bc5eb4b817f1759f0cc2\",\n      \"accountId\": \"brainbackdoor@modoohakwon.com\",\n      \"questionTitle\": \"문의타이틀\",\n      \"questionContent\": \"문의컨텐츠\",\n      \"__v\": 0\n  }\n]",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE \n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 1\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/question.js",
    "groupTitle": "Question"
  },
  {
    "type": "get",
    "url": "/api/question",
    "title": "Get Question Information [Dev]",
    "version": "0.1.0",
    "name": "GetQuestions",
    "group": "Question",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n[\n   {\n       \"date\": {\n           \"created\": \"2018-03-09T16:54:54.481Z\",\n           \"edited\": \"2018-03-09T16:54:54.481Z\"\n       },\n       \"receivers\": [\n           {\n               \"receiverId\": \"bbd@educhoice.com\",\n               \"_id\": \"5aa2bc5eb4b817f1759f0cc1\"\n           },\n           {\n               \"receiverId\": \"bbd2@educhoice.com\",\n               \"_id\": \"5aa2bc5eb4b817f1759f0cc0\"\n           }\n       ],\n       \"is_edited\": false,\n       \"_id\": \"5aa2bc5eb4b817f1759f0cc2\",\n       \"accountId\": \"brainbackdoor@modoohakwon.com\",\n       \"questionTitle\": \"문의타이틀\",\n       \"questionContent\": \"문의컨텐츠\",\n       \"__v\": 0\n   }\n]",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/question.js",
    "groupTitle": "Question"
  },
  {
    "type": "post",
    "url": "/api/question",
    "title": "Post Question information",
    "version": "0.1.0",
    "name": "PostQuestionInformation",
    "group": "Question",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "Array",
            "optional": false,
            "field": "receivers",
            "description": "<p>학원 ID</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "questionTitle",
            "description": "<p>문의 제목</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "questionContent",
            "description": "<p>문의 내용</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT PARENTS\n{\n  \"error\": \"NOT PARENTS\",\n  \"code\" : 3\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/question.js",
    "groupTitle": "Question"
  },
  {
    "type": "put",
    "url": "/api/question/:id",
    "title": "Put Question Information [Dev]",
    "version": "0.1.0",
    "name": "PutQuestion",
    "group": "Question",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "questionTitle",
            "description": "<p>문의 제목</p>"
          },
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "questionContent",
            "description": "<p>문의 내용</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n  \"success\": true,\n  \"question\": {\n      \"date\": {\n          \"created\": \"2018-03-10T07:14:16.226Z\",\n          \"edited\": \"2018-03-10T07:22:51.906Z\"\n      },\n      \"receivers\": [\n          {\n              \"receiverId\": \"bbd@educhoice.com\",\n              \"_id\": \"5aa385c815958aece4131e61\"\n          },\n          {\n              \"receiverId\": \"bbd2@educhoice.com\",\n              \"_id\": \"5aa385c815958aece4131e60\"\n          }\n      ],\n      \"is_edited\": true,\n      \"_id\": \"5aa385c815958aece4131e62\",\n      \"accountId\": \"hohsso@modoohakwon.com\",\n      \"questionTitle\": \"문의타이틀수정\",\n      \"questionContent\": \"문의컨텐츠수정\",\n      \"__v\": 0\n  }\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 4\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 5\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/question.js",
    "groupTitle": "Question"
  },
  {
    "type": "delete",
    "url": "/api/reply/:id",
    "title": "Delete Reply Information [Dev]",
    "version": "0.1.0",
    "name": "DeleteReply",
    "group": "Reply",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 4\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/reply.js",
    "groupTitle": "Reply"
  },
  {
    "type": "get",
    "url": "/api/reply",
    "title": "Get Reply Information [Dev]",
    "version": "0.1.0",
    "name": "GetRepies",
    "group": "Reply",
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n\n[\n  {\n      \"date\": {\n          \"created\": \"2018-03-09T19:05:45.542Z\",\n          \"edited\": \"2018-03-09T19:05:45.542Z\"\n      },\n      \"is_edited\": false,\n      \"_id\": \"5aa2db096e3e895a54d38e9a\",\n      \"accountId\": \"brainbackdoor@modoohakwon.com\",\n      \"role\": \"parents\",\n      \"accountName\": \"bbd\",\n      \"questionId\": \"5aa2bc5eb4b817f1759f0cc2\",\n      \"answerId\": \"5aa2d83a9981b98349922da0\",\n      \"content\": \"댓글 컨텐츠\",\n      \"__v\": 0\n  }\n]",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/reply.js",
    "groupTitle": "Reply"
  },
  {
    "type": "post",
    "url": "/api/reply/:answerId",
    "title": "Post Reply information",
    "version": "0.1.0",
    "name": "PostReply",
    "group": "Reply",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>댓글내용</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 201 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/reply.js",
    "groupTitle": "Reply"
  },
  {
    "type": "put",
    "url": "/api/reply/:id",
    "title": "Put Reply Information [Dev]",
    "version": "0.1.0",
    "name": "PutReply",
    "group": "Reply",
    "parameter": {
      "fields": {
        "Parameter": [
          {
            "group": "Parameter",
            "type": "String",
            "optional": false,
            "field": "content",
            "description": "<p>댓글 내용</p>"
          }
        ]
      }
    },
    "success": {
      "examples": [
        {
          "title": "Success-Response:",
          "content": "    HTTP/1.1 200 OK\n{\n     \"success\": true\n}",
          "type": "json"
        }
      ]
    },
    "error": {
      "fields": {
        "Error 4xx": [
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "INVALID",
            "description": "<p>ID</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "EMPTY",
            "description": "<p>CONTENTS</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NOT",
            "description": "<p>LOGGED IN</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "NO",
            "description": "<p>RESOURCE</p>"
          },
          {
            "group": "Error 4xx",
            "optional": false,
            "field": "PERMISSION",
            "description": "<p>FAILURE</p>"
          }
        ]
      },
      "examples": [
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 INVALID ID\n{\n  \"error\": \"INVALID ID\",\n  \"code\" : 1\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 400 EMPTY CONTENTS\n{\n  \"error\": \"EMPTY CONTENTS\",\n  \"code\" : 2\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 NOT LOGGED IN\n{\n  \"error\": \"NOT LOGGED IN\",\n  \"code\" : 3\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 404 NO RESOURCE\n{\n  \"error\": \"NO RESOURCE\",\n  \"code\" : 4\n}",
          "type": "json"
        },
        {
          "title": "Error-Response:",
          "content": "HTTP/1.1 403 PERMISSION FAILURE\n{\n  \"error\": \"PERMISSION FAILURE\",\n  \"code\" : 5\n}",
          "type": "json"
        }
      ]
    },
    "filename": "server/routes/reply.js",
    "groupTitle": "Reply"
  }
] });
