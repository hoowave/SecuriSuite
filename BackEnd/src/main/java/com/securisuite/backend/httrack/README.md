# Httrack

웹사이트의 동적 콘텐츠를 효과적으로 수집하고 분석할 수 있게 해주어, 보다 광범위한 보안 검사 및 데이터 수집 작업을 지원합니다.

해당 기능을 이용하기 위해서, 먼저 패키지를 설치해야 합니다.</br>
`sudo apt install httrack`
# 1. Execute
## Request

**URL** : `/api/v1/httrack/execute`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "url": "[크롤링 하고자 하는 URL]"
}
```

**데이터 예시**

```json
{
  "url": "http://hoowave.dothome.co.kr"
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "url": "http://hoowave.dothome.co.kr",
    "transUrl": "20240117173342_http_hoowave_dothome_co_kr",
    "cmd": "/bin/sh -c (mkdir /var/www/html/download/files/20240117173342_http_hoowave_dothome_co_kr && httrack http://hoowave.dothome.co.kr -O /var/www/html/download/files/20240117173342_http_hoowave_dothome_co_kr -%v > /var/www/html/download/logs/20240117173342_httrack.txt && zip -r /var/www/html/download/files/20240117173342_http_hoowave_dothome_co_kr.zip /var/www/html/download/files/20240117173342_http_hoowave_dothome_co_kr && rm -rf /var/www/html/download/files/20240117173342_http_hoowave_dothome_co_kr) > /var/www/html/download/logs/20240117173342_tempLog.txt 2>&1 & ",
    "regDts": "20240117173342",
    "logName": "20240117173342_httrack.txt",
    "tempLog": "20240117173342_tempLog.txt"
  },
  "message": null
}
```
* 작업 명령을 내린 URL과 응답받은 regDts를 통해 진행 상태를 확인할 수 있습니다.

**실패 응답**

**조건** : URL이 입력되지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
    "result": "FAIL",
    "data": null,
    "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.httrack.interfaces.HttrackApiController.execute(com.security.securisuite.httrack.interfaces.dto.UrlRequest): [Field error in object 'urlRequest' on field 'url': rejected value []; codes [NotEmpty.urlRequest.url,NotEmpty.url,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [urlRequest.url,url]; arguments []; default message [url]]; default message [URL은 필수값입니다.]] "
}
```

# 2. Completion
## Request

**URL** : `/api/v1/httrack/completion`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "url": "[execute 작업 명령을 내린 URL]",
  "regDts": "[execute 작업을 통해 전달받은 regDts]"
}
```

**데이터 예시**

```json
{
  "url": "http://hoowave.dothome.co.kr",
  "regDts": "20240117173850"
}
```

## Response

**Code** : `200 OK`

**성공 응답 1**

```json
{
  "result": "SUCCESS",
  "data": {
    "url": "http://hoowave.dothome.co.kr",
    "regDts": "20240117173851",
    "transUrl": "http_hoowave_dothome_co_kr",
    "fileName": "20240117173851_http_hoowave_dothome_co_kr.zip",
    "logName": "20240117173851_httrack.txt",
    "tempLog": "20240117173851_tempLog.txt",
    "complete": false,
    "message": "유효한 작업이 아닙니다."
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
    "url": "http://hoowave.dothome.co.kr",
    "regDts": "20240117173850",
    "transUrl": "http_hoowave_dothome_co_kr",
    "fileName": "20240117173850_http_hoowave_dothome_co_kr.zip",
    "logName": "20240117173850_httrack.txt",
    "tempLog": "20240117173850_tempLog.txt",
    "complete": false,
    "message": "작업이 진행중입니다."
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
    "url": "http://hoowave.dothome.co.kr",
    "regDts": "20240117173850",
    "transUrl": "http_hoowave_dothome_co_kr",
    "fileName": "20240117173850_http_hoowave_dothome_co_kr.zip",
    "logName": "20240117173850_httrack.txt",
    "tempLog": "20240117173850_tempLog.txt",
    "complete": true,
    "message": "작업이 완료되었습니다."
  },
  "message": null
}
```
* 작업이 완료된 경우

# 3. Wget
## Request

**URL** : `/api/v1/httrack/wget`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "url": "[미러링 하고자 하는 URL]"
}
```

**데이터 예시**

```json
{
  "url": "http://hoowave.dothome.co.kr"
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "url": "http://hoowave.dothome.co.kr",
    "transUrl": "20240117175830_http_hoowave_dothome_co_kr",
    "cmd": "/bin/sh -c mkdir /var/www/html/download/files/20240117175830_http_hoowave_dothome_co_kr && wget -P /var/www/html/download/files/20240117175830_http_hoowave_dothome_co_kr -m http://hoowave.dothome.co.kr > /var/www/html/download/logs/20240117175830_wget.txt 2>&1 && zip -r /var/www/html/download/files/20240117175830_http_hoowave_dothome_co_kr.zip /var/www/html/download/files/20240117175830_http_hoowave_dothome_co_kr && rm -rf /var/www/html/download/files/20240117175830_http_hoowave_dothome_co_kr ",
    "regDts": "20240117175830",
    "logName": "20240117175830_wget.txt",
    "fileName": "20240117175830_20240117175830_http_hoowave_dothome_co_kr.zip"
  },
  "message": null
}
```
* 정적인 페이지를 탐색하기 때문에, Httrack보다 비교적 속도가 빠릅니다.

**실패 응답**

**조건** : URL이 입력되지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.httrack.interfaces.HttrackApiController.wget(com.security.securisuite.httrack.interfaces.dto.UrlRequest): [Field error in object 'urlRequest' on field 'url': rejected value []; codes [NotEmpty.urlRequest.url,NotEmpty.url,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [urlRequest.url,url]; arguments []; default message [url]]; default message [URL은 필수값입니다.]] "
}
```

### [<- Architecture & API](../README.md)