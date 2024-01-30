# Crunch

패스워드 생성 및 관리에 사용되는 도구로, 맞춤형 단어 목록을 생성하는 데 사용합니다.

# 1. Execute
## Request

**URL** : `/api/v1/crunch/execute`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "minWord": "[최소길이]",
  "maxWord": "[최대길이]",
  "words": "[모든문자]"
}
```

**데이터 예시**

```json
{
  "minWord": 8,
  "maxWord": 8,
  "words": "0123456789"
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "minWord": 8,
    "maxWord": 8,
    "words": "0123456789",
    "cmd": "/bin/sh -c crunch 8 8 0123456789 -o ./logs/20240112155626_crunch.txt > ./logs/20240112155626_crunch_output.txt 2>&1 & ",
    "regDts": "20240112155626",
    "logName": "20240112155626_crunch.txt",
    "log": "Crunch will now generate the following amount of data: 900000000 bytes\n858 MB\n0 GB\n0 TB\n0 PB\nCrunch will now generate the following number of lines: 100000000 \n",
    "crunchResult": {
      "amountByte": "900000000",
      "amountMb": "858",
      "amountGb": "0",
      "amountTb": "0",
      "amountPb": "0",
      "amountLine": "100000000"
    }
  },
  "message": null
}
```
* 응답받은 regDts를 통해 진행 상태를 확인할 수 있습니다. 

**실패 응답 1**

**조건** : 최소 길이가 0이하 이거나 13이상일 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.crunch.interfaces.CrunchApiController.execute(com.security.securisuite.crunch.interfaces.dto.CrunchDto$CrunchRequest): [Field error in object 'crunchRequest' on field 'minWord': rejected value [0]; codes [Min.crunchRequest.minWord,Min.minWord,Min.int,Min]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [crunchRequest.minWord,minWord]; arguments []; default message [minWord],1]; default message [최소값은 1 이상이어야 합니다.]] "
}
```

**실패 응답 2**

**조건** : 최대 길이가 0이하 이거나 13이상일 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.crunch.interfaces.CrunchApiController.execute(com.security.securisuite.crunch.interfaces.dto.CrunchDto$CrunchRequest): [Field error in object 'crunchRequest' on field 'maxWord': rejected value [13]; codes [Max.crunchRequest.maxWord,Max.maxWord,Max.int,Max]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [crunchRequest.maxWord,maxWord]; arguments []; default message [maxWord],12]; default message [최대값은 12 이하여야 합니다.]] "
}
```

**실패 응답 3**

**조건** : 문자가 입력되지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.crunch.interfaces.CrunchApiController.execute(com.security.securisuite.crunch.interfaces.dto.CrunchDto$CrunchRequest): [Field error in object 'crunchRequest' on field 'words': rejected value []; codes [Size.crunchRequest.words,Size.words,Size.java.lang.String,Size]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [crunchRequest.words,words]; arguments []; default message [words],2147483647,1]; default message [문자열은 필수값입니다.]] "
}
```

# 2. Progress
## Request

**URL** : `/api/v1/crunch/progress`

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
  "regDts": "20240112155626"
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "regDts": "20240112160420",
    "log": "crunch:  23% completed generating output\n",
    "percent": "23%"
  },
  "message": null
}
```
* 0% ~ 100%의 값을 전달받을 수 있습니다.

### [<- Architecture & API](../../../../../../../../../../BackEnd/src/main/java/com/securisuite/backend/README.md)