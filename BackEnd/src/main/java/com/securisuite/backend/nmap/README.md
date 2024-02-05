# Nmap

네트워크 탐색 및 보안 감사에 사용되는 오픈 소스 도구로, 네트워크의 호스트 탐지, 서비스 및 운영 체제 감지 등에 사용합니다.

## Request

**URL** : `/api/v1/nmap/execute`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "option": "[ALL 또는 SET]",
  "ip": "[아이피]",
  "port": "[포트]"
}
```

**데이터 예시**

```json
{
  "option": "ALL",
  "ip": "172.16.234.1"
}
```
또는
```json
{
  "option": "SET",
  "ip": "172.16.234.1",
  "port": "80"
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "option": "ALL",
    "ip": "172.16.234.1",
    "port": null,
    "cmd": "nmap -all 172.16.234.1 ",
    "regDts": "20240112154624",
    "logName": "20240112154624_nmap.txt",
    "log": "Starting Nmap 7.94SVN ( https://nmap.org ) at 2024-01-12 15:46 KST\nNmap scan report for 172.16.234.1\nHost is up (0.0021s latency).\nNot shown: 999 closed tcp ports (conn-refused)\nPORT     STATE SERVICE\n3306/tcp open  mysql\n\nNmap done: 1 IP address (1 host up) scanned in 0.67 seconds\n",
    "time": "0.67",
    "result": {
      "1": {
        "port": "3306",
        "state": "open",
        "service": "mysql"
      }
    }
  },
  "message": null
}
```

**실패 응답 1**

**조건** : IP가 없는 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.nmap.interfaces.NmapApiController.execute(com.security.securisuite.nmap.interfaces.dto.NmapDto$NmapRequest): [Field error in object 'nmapRequest' on field 'ip': rejected value []; codes [NotEmpty.nmapRequest.ip,NotEmpty.ip,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [nmapRequest.ip,ip]; arguments []; default message [ip]]; default message [IP는 필수값입니다.]] "
}
```

**실패 응답 2**

**조건** : 포트가 지정되지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "포트 지정은 필수입니다."
}
```

### [<- Architecture & API](../README.md)