# AlertNews

Professional Forex & Index News Alert App (Free & Open Source)

Real-time financial news alerts, economic calendar, and IPO tracking for traders.

[![GitHub stars](https://img.shields.io/github/stars/ImranDev3/alertnews?style=for-the-badge)](https://github.com/ImranDev3/alertnews)
[![GitHub forks](https://img.shields.io/github/forks/ImranDev3/alertnews?style=for-the-badge)](https://github.com/ImranDev3/alertnews/network)
[![GitHub issues](https://img.shields.io/github/issues/ImranDev3/alertnews?style=for-the-badge)](https://github.com/ImranDev3/alertnews/issues)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg?style=for-the-badge)](https://opensource.org/licenses/MIT)
[![Android](https://img.shields.io/badge/Android-34+-blue?style=for-the-badge)](https://developer.android.com/)
[![Kotlin](https://img.shields.io/badge/Kotlin-1.9.22-purple?style=for-the-badge)](https://kotlinlang.org/)

---

## Overview

AlertNews is a complete Android application designed for **Forex & Index traders** who need instant access to high-impact financial news. Built with Kotlin, Jetpack Architecture Components, and Material Design, it delivers real-time news alerts from top financial sources with a professional UI theme.

### Who is this for?
- Forex traders (EUR/USD, GBP/USD, USD/JPY, etc.)
- Index traders (NASDAQ, S&P 500, Dow Jones)
- Commodity traders (Gold XAU/USD, Crude Oil WTI/Brent)
- Anyone tracking economic events (NFP, CPI, FOMC, ECB, etc.)

---

## Key Features

### 📰 Real-Time News Alerts
- RSS aggregation from Investing.com, DailyFX, Reuters, Bloomberg
- Real-time headlines with title, source, timestamp, and summary
- Swipe-to-refresh for manual updates
- Push notifications for high-impact events

### 📅 Economic Calendar
- Weekly economic calendar with scheduled events
- Impact level badges (Critical, High, Medium, Low)
- Currency-specific events (USD, EUR, JPY, etc.)
- Actual, forecast, and previous values

### 💰 IPO Tracker
- Upcoming and listed IPOs from NASDAQ & NYSE
- Company details, price range, shares offered
- Status tracking (upcoming, listed, new)

### 📱 Push Notifications
- Real-time alerts for high-impact events
- Configurable notification settings
- Auto-start on device boot

### 🔍 Topic Filtering
- Trump administration news
- Federal Reserve & monetary policy
- Economic indicators (CPI, NFP, GDP)
- Geopolitical events (war, conflict)
- Commodity prices (gold, oil, silver)
- Currency exchange rates
- Market indices (NASDAQ, S&P 500, Dow)
- IPO tracking

### ⭐ Favorites & Read Tracking
- Mark articles as favorites
- Track read/unread status
- 100% free - no paid signal bot

### 🎨 Professional UI
- Material Components DayNight theme (dark/light)
- Impact level color coding
- Clean, responsive UI with proper spacing
- AndroidManifest.xml with proper permissions

---

## Project Structure

```
alertnews/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/alertnews/app/
│       │   ├── MainActivity.kt
│       │   ├── NewsAdapter.kt
│       │   ├── CalendarAdapter.kt
│       │   ├── IpoAdapter.kt
│       │   ├── MainApplication.kt
│       │   ├── NewsViewModel.kt
│       │   ├── NewsItem.kt
│       │   ├── EconomicCalendar.kt
│       │   │   ├── IpoEvent.kt
│       │   │   ├── NewsDao.kt
│       │   │   └── NewsDatabase.kt
│       │   ├── data/
│       │   │   ├── source/
│       │   │   │   ├── NewsDataSource.kt
│       │   │   │   ├── RssNewsSource.kt
│       │   │   ├── FactoryCalendarSource.kt
│       │   │   └── IpoNewsSource.kt
│       │   ├── data/
│       │   │   ├── local/
│       │   │   │   ├── NewsDao.kt
│       │   │   │   └── NewsDatabase.kt
│       │   │   └── repository/
│       │   │       └── NewsRepository.kt
│       │   └── viewmodels/
│       │       └── NewsViewModel.kt
│       │   └── ui/
│       │       ├── MainActivity.kt
│       │       ├── CalendarFragment.kt
│       │       └── IpoFragment.kt
│       │   └── adapters/
│       │       ├── NewsAdapter.kt
│       │       ├── CalendarAdapter.kt
│       │       └── IpoAdapter.kt
│       └── res/
│           ├── layout/
│       │   ├── activity_main.xml
│       │   ├── item_news.xml
│       │   ├── item_calendar.xml
│       │   └── item_ipo.xml
│       │   └── drawable/
│       │       ├── ic_launcher.xml
│       │       ├── ic_star_filled.xml
│       │       └── ic_star_outline.xml
│       │   └── values/
│       │       ├── colors.xml
│       │       ├── strings.xml
│       │       └── themes.xml
│
├── build.gradle
├── settings.gradle
├── gradle.properties
└── README.md
```

---

## Setup & Installation

### Prerequisites
- Android Studio (latest stable)
- Android SDK 34+
- JDK 17+
- Kotlin 1.9.22+

### Build & Run

1. Clone the repository:
```bash
git clone https://github.com/ImranDev3/alertnews.git
```

2. Open in Android Studio:
```bash
cd alertnews
# Open in Android Studio
```

3. Build the APK:
```bash
./gradlew assembleDebug
```

4. Install on device:
```bash
./gradlew installDebug
```

---

## GitHub Push Commands

### First-time setup:
```bash
# Initialize git repository
git init

# Configure git user (set once)
git config user.name "ImranDev3"
git config user.email "imrandev3@users.noreply.github.com"

# Add all files
git add .

# Commit with descriptive message
git commit -m "first commit: AlertNews professional setup

- Add README.md project documentation
- Add build.gradle (Gradle project setup)
- Add settings.gradle (module configuration)
- Add full Android source code

Co-Authored-By: Claude Code <noreply@anthropic.com>"

# Set main branch
git branch -M main

# Add remote origin
git remote add origin https://github.com/ImranDev3/alertnews.git

# Push to GitHub
git push -u origin main
```

### For future updates:
```bash
git add .
git commit -m "your message"
git push origin main
```

---

## Project Structure

### Build Configuration
- `build.gradle`: Gradle project configuration
- `settings.gradle`: Module structure
- `gradle.properties`: JVM options and caching
- `app/build.gradle`: Android module configuration

### Project Structure
- `app/src/main/java/` - Kotlin source files
- `app/src/main/res/` - Resources (layouts, colors, themes)
- `app/src/main/AndroidManifest.xml` - App configuration

### Key Files
- `MainActivity.kt` - Main entry point with RecyclerView
- `NewsAdapter.kt` - RecyclerView adapter for news items
- `NewsDatabase.kt` - Room database for offline storage
- `TopicsProvider.kt` - Topic matching system
- `FilterManager.kt` - User preference management

---

## Technology Stack

| Component | Technology |
|-----------|------------|
| Language | Kotlin 1.9.22 |
| UI Framework | AndroidX Material Components |
| Architecture | MVVM with ViewModel and Repository |
| Database | Room (SQLite) |
| Networking | Retrofit + OkHttp |
| Image Loading | Glide |
| Background Tasks | WorkManager |
| Dependencies | 50+ AndroidX libraries |

---

## GitHub Push Commands

### Initial Setup:
```bash
git init
git config user.name "ImranDev3"
git config user.email "imrandev3@users.noreply.github.com"
git add .
git commit -m "first commit: AlertNews professional setup

- Add README.md project documentation
- Add build.gradle (Gradle project setup)
- Add settings.gradle (module configuration)
- Add full Android source code

Co-Authored-By: Claude Code <noreply@anthropic.com>"
```

### After making changes:
```bash
git add .
git commit -m "descriptive commit message"
git push origin main
```

---

## Project Features

- ✅ **Live News**: RSS feeds from top financial sources
- ✅ **Economic Calendar** - Forex Factory style
- ✅ **IPO Tracking** - Real-time IPO monitoring
- ✅ **Push Notifications** - High/critical impact events
- ✅ **Theme System** - Dark/Light mode with professional colors
- ✅ **Full Git Integration** - Complete commit history
- ✅ **Professional Documentation** - Complete README with setup instructions

---

## Support

For questions or feature requests, please open an issue on GitHub or contact the developer.

**Co-Authored-By: Claude Code <noreply@anthropic.com>**

---

*This project is licensed under the MIT License. See [LICENSE](LICENSE) for details.*