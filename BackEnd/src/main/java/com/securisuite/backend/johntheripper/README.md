# Httrack

암호의 강도를 테스트하고 복구하는데 사용될 패스워드 크래킹 도구입니다.

# 1. Execute
## Request

**URL** : `/api/v1/john/execute`

**Method** : `POST`

**Content-Type** : `multipart/form-data`

**Auth required** : NO

**데이터 제약 1**

```
{
  targetFile: [압축파일 또는 문서파일]
  text: '{"johnOption": "[DEFAULT 또는 CUSTOM]"}'
}
```

**데이터 제약 1**
```
{
  targetFile: [압축파일 또는 문서파일]
  text: '{"johnOption": "[DEFAULT 또는 CUSTOM]"}'
}
```

**데이터 예시 1**

```
{
  targetFile: (testimg.zip)
  text: '{"johnOption": "DEFAULT"}'
}
```

**데이터 제약 2**
```
{
  targetFile: [압축파일 또는 문서파일]
  text: '{"johnOption": "[DEFAULT 또는 CUSTOM]"}'
  customList: [단어 목록]
}
```
johnOption이 CUSTOM인 경우, customList을 필요로 합니다.

**데이터 예시 2**
```
{
  targetFile: (testimg.zip)
  text: '{"johnOption": "CUSTOM"}'
  customList: (20240119164309_crunch.txt)
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "johnOption": "CUSTOM",
    "johnType": "ARCHIVE",
    "cmd": "/bin/sh -c zip2john /var/www/html/upload/20240119165555_testimg.zip > /var/www/html/download/logs/20240119165555_hash.txt && john -w:/var/www/html/upload/20240119165555_20240119164309_crunch.txt /var/www/html/download/logs/20240119165555_hash.txt && john /var/www/html/download/logs/20240119165555_hash.txt --show > /var/www/html/download/logs/20240119165555_johnTheRipper.txt ",
    "regDts": "20240119165555",
    "logName": "20240119165555_johnTheRipper.txt",
    "hashName": "20240119165555_hash.txt",
    "targetFileName": "20240119165555_testimg.zip",
    "customListName": "20240119165555_20240119164309_crunch.txt"
  },
  "message": null
}
```
* 응답받은 regDts를 통해 진행 상태를 확인할 수 있습니다.

**실패 응답 1**

**조건** : targetFile이 입력되지 않았거나 지원하지 않는 확장자를 입력했을 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "지원하지 않는 확장자입니다."
}
```

**실패 응답 2**

**조건** : johnOption을 입력되지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.johntheripper.interfaces.JohnApiController.execute(com.security.securisuite.johntheripper.interfaces.dto.JohnDto$JohnRequest): [Field error in object 'johnRequest' on field 'johnOption': rejected value [null]; codes [NotNull.johnRequest.johnOption,NotNull.johnOption,NotNull.com.security.securisuite.johntheripper.domain.JohnOption,NotNull]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [johnRequest.johnOption,johnOption]; arguments []; default message [johnOption]]; default message [옵션은 필수값입니다.]] "
}
```

**실패 응답 3**

**조건** : johnOption을 CUSTOM으로 하고 customList이 입력되지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "사용자 리스트는 필수입니다."
}
```

# 2. Completion
## Request

**URL** : `/api/v1/john/completion`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "regDts": "[execute 작업을 통해 전달받은 regDts]"
}
```

**데이터 예시**

```json
{
  "regDts": "20240119165250"
}
```

## Response

**Code** : `200 OK`

**성공 응답 1**

```json
{
  "result": "SUCCESS",
  "data": {
    "regDts": "20240119165556",
    "logName": "20240119165556_johnTheRipper.txt",
    "hashName": "20240119165556_hash.txt",
    "complete": false,
    "message": "유효한 작업이 아닙니다.",
    "password": null
  },
  "message": null
}
```
* 해당 작업이 존재하지 않을 경우

**성공 응답 2**

```json
{
  "result": "SUCCESS",
  "data": {
    "regDts": "20240119165555",
    "logName": "20240119165555_johnTheRipper.txt",
    "hashName": "20240119165555_hash.txt",
    "complete": true,
    "message": "작업이 진행중입니다.",
    "password": null
  },
  "message": null
}
```
* 작업이 진행중인 경우

**성공 응답 3**

```json
{
  "result": "SUCCESS",
  "data": {
    "regDts": "20240119165555",
    "logName": "20240119165555_johnTheRipper.txt",
    "hashName": "20240119165555_hash.txt",
    "complete": true,
    "message": "작업이 완료되었습니다.",
    "password": "hoowave"
  },
  "message": null
}
```
* 작업이 완료된 경우


### [<- Architecture & API](../../../../../../../../../../BackEnd/src/main/java/com/securisuite/backend/README.md)