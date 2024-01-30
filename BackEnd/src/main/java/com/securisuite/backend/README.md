## API
### * [Nmap](nmap/README.md) : `/api/v1/nmap`
### * [Crunch](crunch/README.md) : `/api/v1/crunch`
### * [System](system/README.md) : `/api/v1/system`
### * [Httrack](httrack/README.md) : `/api/v1/httrack`
### * [JohnTheRipper](johntheripper/README.md) `/api/v1/john`

## 데이터 처리 아키텍처
본 프로젝트는 interfaces, facade, domain, infrastructure의 네 가지 주요 계층으로 구성되어 있으며, 이들 간의 상호 작용을 통해 데이터 처리가 이루어집니다.</br>
각 도메인에서 데이터 처리 과정은 다음과 같은 순서로 진행됩니다:
1. Interfaces 계층</br>
이 계층은 사용자 또는 외부 시스템으로부터 데이터를 수신합니다.</br>
수신된 데이터는 DTO(Data Transfer Object) 형태의 Request로 캡슐화되어 다음 단계로 전달됩니다.</br>
2. Facade 계층</br>
DTO(Request)를 받아 Command 객체로 변환하는 역할을 합니다.</br>
이곳에서 다양한 외부 시스템과의 연계를 관리하고 통합하는 중앙 지점 역할을 합니다.</br>
3. Domain 계층</br>
Command 객체를 Domain 객체로 변환합니다.</br>
이곳에서 비즈니스 로직이 구현되며, 데이터의 실제 처리가 이루어집니다.</br>
Domain 계층은 핵심 비즈니스 규칙과 데이터의 무결성을 관리합니다.</br>
데이터 처리 결과를 Info 객체로 변환합니다.</br>
Info 객체는 처리된 데이터를 요약하거나 표현하는데 사용됩니다.</br>
4. Infrastructure 계층(예정)</br>
이 프로젝트에서는 Spring Data JPA를 사용하여 MariaDB 데이터베이스와의 상호작용을 관리합니다.
5. 응답</br>
처리된 데이터는 최종적으로 DTO(Response) 형태로 변환되어 사용자 또는 요청자에게 응답됩니다.

### [<- 프로젝트 개요](../../../../../../../README.md)