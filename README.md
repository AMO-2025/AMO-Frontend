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

- 사용자 인증 (로그인/회원가입)
- 맵 화면에서 다양한 위치 선택 (집, 공원, 식당, 학교)
- 위치별 게임 플레이
- 포인트 시스템
- 단계별 해금 시스템

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