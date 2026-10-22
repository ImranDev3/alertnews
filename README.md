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
- RSS aggregation from Investing.com, DailyFX, Reuters, Bloomberg, MarketWatch
- Real-time headlines with title, source, timestamp, and summary
- Swipe-to-refresh for manual updates
- Push notifications for high-impact events
- Topic filtering A-Z (18 topics: Trump, Fed, War, Inflation/CPI, NFP/Jobs, GDP, Gold/XAU, Oil/Crude, DXY/Dollar, NASDAQ, SPX/S&P500, Dow Jones, IPO, Central Banks, Economy, Politics, Crypto, Market Crash, Bailout/Stimulus, China)

### 📅 Economic Calendar
- Forex Factory style economic calendar
- Weekly economic calendar with scheduled events
- Impact level badges (Critical, High, Medium, Low)
- Currency-specific events (USD, EUR, JPY, etc.)
- Actual, forecast, and previous values
- Countdown timer until event

### 💰 IPO Tracker
- Upcoming and listed IPOs from NASDAQ & NYSE
- Company details, price range, shares offered
- Status tracking (upcoming, listed, new)
- Real-time IPO calendar integration

### 📱 Push Notifications
- Real-time alerts for high-impact events
- Impact-based notification priority (Critical → Max, High → High)
- Auto-start on device boot via BootReceiver
- Configurable notification settings
- Foreground service for persistent alerts

### 🎵 Background Music
- Optional ambient background music
- Volume control seek bar
- Custom music URL support
- Enable/disable toggle in settings
- Music persists across app lifecycle

### 🔍 Topic Filtering
- 18 topics A-Z with color coding:
  - Trump, Fed, War, Inflation/CPI, NFP/Jobs, GDP
  - Gold/XAU, Oil/Crude, DXY/Dollar, NASDAQ, SPX/S&P500, Dow Jones
  - IPO, Central Banks, Economy, Politics, Crypto
  - Market Crash, Bailout/Stimulus, China
- Checkbox UI for quick topic selection
- Real-time topic matching on incoming news

### ⭐ Favorites & Read Tracking
- Mark articles as favorites with star icon
- Track read/unread status
- Favorites-only filter mode

### 🎨 Professional UI
- Material Components DayNight theme (dark/light mode)
- Impact level color coding (red=high, orange=medium, green=low)
- Market sessions display (Sydney, Tokyo, London, New York, Los Angeles)
- Timezone-aware display (user-selectable timezone)
- Clean, responsive UI with proper spacing
- Full settings screen with all customization options

---

## Project Structure

```
alertnews/
├── app/
│   ├── build.gradle          # Module build with all dependencies
│   ├── proguard-rules.pro
│   └── src/
│       └── main/
│           ├── AndroidManifest.xml
│           ├── java/com/alertnews/app/
│           │   ├── MainActivity.kt          # Main entry, RecyclerView + FAB
│           │   ├── MusicPlayer.kt           # Background music player
│           │   ├── NotificationHelper.kt    # Channel + notification display
│           │   ├── NotificationService.kt   # Foreground alert service
│           │   ├── BootReceiver.kt          # Auto-start on boot
│           │   ├── MainApplication.kt       # App class, repo init
│           │   ├── TopicsProvider.kt        # Topic matching system
│           │   ├── FilterManager.kt         # Filter state management
│           │   ├── ui/
│           │   │   ├── SettingsFragment.kt  # Full settings UI
│           │   │   ├── CalendarFragment.kt  # Calendar view
│           │   │   └── IpoFragment.kt       # IPO view
│           │   ├── adapters/
│           │   │   ├── NewsAdapter.kt       # News RecyclerView adapter
│           │   │   ├── CalendarAdapter.kt   # Calendar adapter
│           │   │   ├── IpoAdapter.kt        # IPO adapter
│           │   │   └── TopicAdapter.kt      # A-Z topic selector
│           │   ├── data/
│           │   │   ├── model/
│           │   │   │   ├── NewsItem.kt
│           │   │   │   ├── EconomicCalendar.kt
│           │   │   │   ├── ForecastEvent.kt
│           │   │   │   ├── MarketSession.kt
│           │   │   │   ├── Session.kt
│           │   │   │   ├── Topic.kt
│           │   │   │   └── UserSettings.kt
│           │   │   ├── repository/
│           │   │   │   └── NewsRepository.kt
│           │   │   ├── source/
│           │   │   │   ├── NewsDataSource.kt
│           │   │   │   ├── RssNewsSource.kt
│           │   │   │   ├── FactoryCalendarSource.kt
│         │   │   │   └── IpoNewsSource.kt
│           │   │   └── local/
│           │   │       ├── NewsDao.kt
│           │   │       └── NewsDatabase.kt
│           │   ├── viewmodels/
│           │   │   └── NewsViewModel.kt
│           │   └── util/
│           │       └── TimezoneUtil.kt
│           ├── res/
│           │   ├── layout/
│           │   │   ├── activity_main.xml
│           │   │   ├── fragment_settings.xml
│           │   │   ├── fragment_calendar.xml
│           │   │   ├── fragment_ipo.xml
│           │   │   ├── item_news.xml
│           │   │   ├── item_calendar.xml
│           │   │   ├── item_ipo.xml
│           │   │   └── item_topic.xml
│           │   ├── drawable/
│           │   │   ├── ic_star_filled.xml
│           │   │   ├── ic_star_outline.xml
│           │   │   ├── ic_add.xml
│           │   │   ├── ic_notification.xml
│           │   │   ├── ic_music.xml
│           │   │   └── ic_music_note.xml
│           │   └── values/
│           │       ├── colors.xml
│           │       ├── themes.xml
│           │       ├── strings.xml
│           │       └── dimens.xml
└── build.gradle          # Project-level Gradle
├── settings.gradle
└── gradle.properties
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
git commit -m "first commit: AlertNews professional setup"

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
git commit -m "feat: description of changes"
git push origin main
```

---

## Technology Stack

| Component | Technology |
|-----------|------------|
| **Language** | Kotlin 1.9.22 |
| **UI Framework** | AndroidX Material Components |
| **Architecture** | MVVM with ViewModel and Repository |
| **Database** | Room (SQLite) |
| **Networking** | Retrofit + OkHttp (RSS parsing) |
| **Image Loading** | Glide |
| **Background Tasks** | WorkManager + Coroutines |
| **State Management** | StateFlow + LiveData |
| **Navigation** | AndroidX Navigation Component |
| **Notifications** | NotificationManager with custom channels |

---

## Contact

- **Email**: dev@alertnews.app
- **GitHub**: [ImranDev3](https://github.com/ImranDev3)
- **Repository**: https://github.com/ImranDev3/alertnews

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

Built with ❤️ for the trading community. Free and open source forever.

---

🤖 Generated with [Claude Code](https://claude.com/claude-code) | Co-Authored-By: Claude Code <noreply@anthropic.com>