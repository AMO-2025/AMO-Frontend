<<<<<<< HEAD
# 🚀 AMO-Frontend (React Native Version)

이 프로젝트는 기존 Kotlin Compose for Desktop으로 개발되었던 AMO-Frontend를 React Native로 포팅한 프론트엔드 애플리케이션입니다.

---

## 📌 프로젝트 정보

- **플랫폼**: React Native (Android/iOS)
- **언어**: TypeScript
- **라이브러리**:
  - React Navigation
  - Axios 또는 Fetch
  - React Native Vector Icons
  - AsyncStorage
- **백엔드 연동**: AMO-Backend (Spring Boot 3.4.3)

---

## 🎯 주요 기능
=======
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
>>>>>>> 9f5f6c37881571bf02366bbbe9477e594ccc51a3

- 사용자 인증 (로그인/회원가입)
- 맵 화면에서 다양한 위치 선택 (집, 공원, 식당, 학교)
- 위치별 게임 플레이
- 포인트 시스템
- 단계별 해금 시스템

<<<<<<< HEAD
---

## 🗂️ 프로젝트 구조

```
AMO-Frontend/
├── android/                  # Android 네이티브 코드
├── ios/                      # iOS 네이티브 코드
├── src/
│   ├── screens/              # 각 화면 컴포넌트
│   │   ├── FirstScreen.tsx
│   │   ├── FifthScreen.tsx
│   │   ├── HomeScreen.tsx
│   │   ├── ParkScreen.tsx
│   │   ├── RestaurantScreen.tsx
│   │   └── SchoolScreen.tsx
│   ├── components/           # 공통 UI 컴포넌트
│   ├── data/                 # 데이터 모델 및 상태 관리
│   └── network/              # API 통신 모듈
├── App.tsx                   # 앱 진입점
├── package.json              # 의존성 및 프로젝트 설정
├── tsconfig.json             # TypeScript 설정
└── README.md
```

---

## ⚙️ 설치 및 실행

### 1. 의존성 설치
```bash
npm install
```

### 2. 앱 실행

#### Android
```bash
npx react-native run-android
```

#### iOS (Mac 필요)
```bash
npx pod-install
npx react-native run-ios
```

---

## 🔗 백엔드 연동

- **백엔드 저장소**: [AMO-Backend](https://github.com/AMO-2025/AMO-Backend)
- **기술 스택**: Java 21, Spring Boot 3.4.3
- **통신 방식**: REST API (JWT 기반 인증)

---

## 🤝 기여 방법

1. 이 저장소를 포크합니다.
2. 새 브랜치를 생성합니다:
   ```bash
   git checkout -b feature/my-feature
   ```
3. 변경 사항을 커밋합니다:
   ```bash
   git commit -m "Add some feature"
   ```
4. 브랜치를 푸시합니다:
   ```bash
   git push origin feature/my-feature
   ```
5. Pull Request를 생성합니다.

---

## 📬 연락처

- **GitHub**: [AMO-2025](https://github.com/AMO-2025)
- **Frontend 담당자**: jino021213@gmail.com
=======
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
>>>>>>> 9f5f6c37881571bf02366bbbe9477e594ccc51a3
