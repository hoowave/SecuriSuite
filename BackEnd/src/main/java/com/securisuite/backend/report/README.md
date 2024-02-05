# Report

프로젝트에서 생성된 결과 파일 및 로그 파일을 다운로드하고 관리할 수 있는 API입니다.

# 1. Dashboard
## Request

**URL** : `/api/v1/report/dashboard`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
}
```

**데이터 예시**

```json
{
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "counts": {
      "crunch": 0,
      "system": 2,
      "john": 0,
      "nmap": 0,
      "httrack": 0
    }
  },
  "message": null
}
```

# 2. Filelist
## Request

**URL** : `/api/v1/report/files`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
}
```

**데이터 예시**

```json
{
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "httrackFileInfos": [
      {
        "httrackRegDts": "20240205052516",
        "httrackTransUrl": "20240205052516_http_hoowave_dothome_co_kr",
        "httrackUrl": "http://hoowave.dothome.co.kr",
        "httrackType": "wget"
      }
    ],
    "crunchFileInfos": {
      "1": {
        "crunchMinWord": 4,
        "crunchMaxWord": 4,
        "crunchWords": "0123456789",
        "crunchRegDts": "20240205052522",
        "crunchLogName": "20240205052522_crunch.txt"
      }
    }
  },
  "message": null
}
```

# 3. Loglist
## Request

**URL** : `/api/v1/report/logs`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
}
```

**데이터 예시**

```json
{
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "logList": [
      {
        "type": "system",
        "logName": "20240205050749_system.txt",
        "regDts": "20240205050749"
      },
      {
        "type": "system",
        "logName": "20240205050745_system.txt",
        "regDts": "20240205050745"
      }
    ]
  },
  "message": null
}
```

### [<- Architecture & API](../README.md)