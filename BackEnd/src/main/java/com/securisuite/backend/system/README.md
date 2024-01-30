# System

시스템에 직접적으로 명령을 내리거나, 프로세스를 제어합니다.

# 1. Execute
## Request

**URL** : `/api/v1/system/execute`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "cmd": "[명령어]"
}
```

**데이터 예시**

```json
{
  "cmd": "ls -al"
}
```

## Response

**Code** : `200 OK`

**성공 응답**

```json
{
  "result": "SUCCESS",
  "data": {
    "cmd": "/bin/sh -c ls -al ",
    "regDts": "20240112161209",
    "logName": "20240112161209_system.txt",
    "result": "total 72\ndrwxr-xr-x 8 hoowave hoowave 4096 Jan 12 13:25 .\ndrwxr-xr-x 3 hoowave hoowave 4096 Jan 12 13:24 ..\n-rw-r--r-- 1 hoowave hoowave 6148 Jan  9 14:44 .DS_Store\n-rw-r--r-- 1 hoowave hoowave  444 Jan  5 15:21 .gitignore\ndrwxr-xr-x 5 hoowave hoowave 4096 Jan  5 15:24 .gradle\ndrwxr-xr-x 3 hoowave hoowave 4096 Jan 12 13:24 .idea\n-rw-r--r-- 1 hoowave hoowave 1125 Jan  5 15:21 HELP.md\ndrwxr-xr-x 9 hoowave hoowave 4096 Jan 12 13:24 build\n-rw-r--r-- 1 hoowave hoowave  797 Jan  5 16:23 build.gradle\ndrwxr-xr-x 3 hoowave hoowave 4096 Jan  5 15:21 gradle\n-rwxr-xr-x 1 hoowave hoowave 8692 Jan  5 15:21 gradlew\n-rw-r--r-- 1 hoowave hoowave 2868 Jan  5 15:21 gradlew.bat\ndrwxr-xr-x 2 hoowave hoowave 4096 Jan 12 16:12 logs\n-rw-r--r-- 1 hoowave hoowave   33 Jan  5 15:21 settings.gradle\ndrwxr-xr-x 4 hoowave hoowave 4096 Jan  9 14:14 src\n"
  },
  "message": null
}
```

**실패 응답**

**조건** : 명령어가 없는 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.system.interfaces.SystemApiController.execute(com.security.securisuite.system.interfaces.dto.SystemDto$SystemRequest): [Field error in object 'systemRequest' on field 'cmd': rejected value []; codes [NotEmpty.systemRequest.cmd,NotEmpty.cmd,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [systemRequest.cmd,cmd]; arguments []; default message [cmd]]; default message [명령어는 필수값입니다.]] "
}
```

# 2. Pid
## Request

**URL** : `/api/v1/system/pid`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "processName": "[프로세스이름]"
}
```

**데이터 예시**

```json
{
  "processName": "crunch"
}
```

## Response

**Code** : `200 OK`

**성공 응답 **

```json
{
  "result": "SUCCESS",
  "data": {
    "processName": "crunch",
    "cmd": "/bin/sh -c ps aux | grep crunch | grep -v grep ",
    "regDts": "20240112161615",
    "logName": "20240112161615_pid.txt",
    "log": "hoowave   128933 10.9  0.1  13460  2176 pts/0    Rl+  16:16   0:00 crunch 8 8 0123456789 -o ./logs/20240112161612_crunch.txt\n",
    "pid": "128933"
  },
  "message": null
}
```

**실패 응답 1**

**조건** : 프로세스이름이 입력되지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.system.interfaces.SystemApiController.pid(com.security.securisuite.system.interfaces.dto.ProcessNameRequest): [Field error in object 'processNameRequest' on field 'processName': rejected value []; codes [NotEmpty.processNameRequest.processName,NotEmpty.processName,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [processNameRequest.processName,processName]; arguments []; default message [processName]]; default message [프로세스명은 필수값입니다.]] "
}
```

**실패 응답 2**

**조건** : 프로세스가 실행중이지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "해당 프로세스는 존재하지 않습니다."
}
```

# 3. Terminate
## Request

**URL** : `/api/v1/system/terminate`

**Method** : `POST`

**Auth required** : NO

**데이터 제약**

```json
{
  "processName": "[프로세스이름]"
}
```

**데이터 예시**

```json
{
  "processName": "crunch"
}
```

## Response

**Code** : `200 OK`

**성공 응답 **

```json
{
  "result": "SUCCESS",
  "data": {
    "processName": "crunch",
    "pid": "131913",
    "cmd": "kill -9 131913 ",
    "regDts": "20240112162226"
  },
  "message": null
}
```

**실패 응답 1**

**조건** : 프로세스이름이 입력되지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "적절하지 않은 요청입니다.Validation failed for argument [0] in public com.security.securisuite.common.response.CommonResponse com.security.securisuite.system.interfaces.SystemApiController.terminate(com.security.securisuite.system.interfaces.dto.ProcessNameRequest): [Field error in object 'processNameRequest' on field 'processName': rejected value []; codes [NotEmpty.processNameRequest.processName,NotEmpty.processName,NotEmpty.java.lang.String,NotEmpty]; arguments [org.springframework.context.support.DefaultMessageSourceResolvable: codes [processNameRequest.processName,processName]; arguments []; default message [processName]]; default message [프로세스명은 필수값입니다.]] "
}
```

**실패 응답 2**

**조건** : 프로세스가 실행중이지 않은 경우

**Code** : `200 OK`

**내용** :

```json
{
  "result": "FAIL",
  "data": null,
  "message": "해당 프로세스는 존재하지 않습니다."
}
```

### [<- Architecture & API](../../../../../../../../../../BackEnd/src/main/java/com/securisuite/backend/README.md)