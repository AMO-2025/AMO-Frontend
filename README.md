# 🚀 AMO-Frontend (Kotlin with Compose for Desktop)

이 프로젝트는 Kotlin과 Compose for Desktop을 이용하여 개발된 프론트엔드 애플리케이션입니다.


## 프로젝트 정보

- **Project**: Gradle - Kotlin DSL
- **Language**: Kotlin
- **Compose**: 1.5.0
- **Packaging**: Jar
- **Kotlin**: 1.9.0
- **Dependencies**: Compose Desktop, Ktor Client, Kotlinx Serialization

## 주요 기능

- 사용자 인증 (로그인/회원가입)
- 맵 화면에서 다양한 위치 선택 (집, 공원, 식당, 학교)
- 위치별 게임 플레이
- 포인트 시스템
- 단계별 해금 시스템

## 프로젝트 구조

- `src/main/kotlin/Main.kt`: 애플리케이션 진입점
- `src/main/kotlin/ui/App.kt`: 메인 앱 컴포넌트
- `src/main/kotlin/ui/screens/`: 화면 컴포넌트
  - `FirstScreen.kt`: 시작 화면
  - `FifthScreen.kt`: 맵 화면
  - `HomeScreen.kt`, `ParkScreen.kt`, `RestaurantScreen.kt`, `SchoolScreen.kt`: 게임 화면
- `src/main/kotlin/data/`: 데이터 모델 및 저장소
- `src/main/kotlin/network/`: API 통신 관련 코드

## 설치 및 실행 방법

### 요구 사항

- JDK 11 이상
- Gradle 7.0 이상

### 설치

```bash
# 저장소 클론
git clone https://github.com/AMO-2025/AMO-Frontend.git
cd AMO-Frontend

# 의존성 설치 및 빌드
./gradlew build
```

### 실행

```bash
./gradlew run
```

## 백엔드 연동

이 프로젝트는 AMO-Backend와 연동되어 작동합니다:
- 백엔드 저장소: [AMO-Backend](https://github.com/AMO-2025/AMO-Backend)
- 백엔드 기술 스택: Java 21, Spring Boot 3.4.3

## 개발 가이드

### 기여 방법

1. 저장소를 포크합니다.
2. 새 브랜치를 생성합니다 (`git checkout -b feature/my-feature`).
3. 변경 사항을 커밋합니다 (`git commit -m 'Add some feature'`).
4. 브랜치를 푸시합니다 (`git push origin feature/my-feature`).
5. Pull Request를 생성합니다.

## 연락처

- GitHub: [AMO-2025](https://github.com/AMO-2025)
- Frontend : jino021213@gmail.com
