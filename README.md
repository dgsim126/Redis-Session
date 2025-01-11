Redis를 활용한 Spring Security 인증 및 인가 정리

사용 기술 및 목표
Spring, Spring Security를 사용하여 인증(Authentication)과 인가(Authorization) 구현.
세션 방식(Session-based)을 통해 사용자 인증 정보를 유지.


Redis를 사용하는 이유
여러 서버를 동시에 운영할 가능성(예: 로드 밸런싱)을 고려하여, 확장성을 지원하는 세션 저장소 필요.
Redis는 분산 환경에서도 세션 데이터를 공유할 수 있어 확장성과 성능이 뛰어남.

구현 방식 요약
Spring Security를 사용하여 사용자 인증/인가 처리.
Spring Session과 Redis를 연동하여 세션 데이터를 Redis에 저장.

구성 요소
Redis: 세션 데이터를 저장하는 중앙 저장소.
Spring Session: Redis와 통합하여 세션을 관리.
Spring Security: 인증과 인가 처리.

장점
확장성: 여러 서버에서 동일한 세션 데이터 공유 가능.
유지보수성: 중앙 집중형 세션 관리로 서버 간 동기화 문제 해결.
성능: Redis의 빠른 읽기/쓰기 속도 덕분에 사용자 경험 향상.
