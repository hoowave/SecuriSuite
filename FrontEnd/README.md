## Interface
### SecuriSuite의 인터페이스는 사용자에게 직관적인 인터페이스를 제공합니다.

## 기능
- 웹 UI를 통한 도구 사용: 사용자는 웹 UI를 통해 nmap, crunch, httrack, johnTheRipper 등의 도구를 직접 사용할 수 있습니다.
- 실시간 데이터 시각화: 대시보드를 통해 사용자의 활동 및 도구 사용 통계를 막대 차트와 둥근 차트로 시각화하여 제공합니다.

## 통신 방법
- FrontEnd 애플리케이션은 AJAX 통신을 활용하여 사용자의 명령어를 실시간으로 BackEnd 서버로 전송하고, 처리 결과를 비동기적으로 받아옵니다.</br>
이를 위해 jQuery를 사용하여 RESTful API 요청을 구성하고, 서버에서 반환된 데이터를 사용자 인터페이스에 동적으로 표시합니다.
- Java Spring 기반의 BackEnd 서버와의 API 통신에는 Spring의 `RestTemplate`을 사용합니다.</br>
이는 서버로부터의 응답을 객체로 쉽게 매핑하고, 전반적인 데이터 처리 과정을 효율화합니다.
### [<- 프로젝트 개요](../README.md)