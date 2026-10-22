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

## Features

### 📰 Live News Alerts
- RSS aggregation from Investing.com, DailyFX, Reuters, MarketWatch
- Real-time headlines with title, source, timestamp, and summary
- Swipe-to-refresh for manual updates
- Full news article opening via in-app browser

### 📊 Forex Factory Economic Calendar
- Upcoming economic events (NFP, CPI, FOMC, ECB, GDP, etc.)
- Impact level badges: **Critical** 🔴 | **High** 🟠 | **Medium** 🟡 | **Low** 🟢
- Currency flag indicators (USD, EUR, GBP, JPY, CHF, AUD, CAD, NZD, CNY)
- Actual vs Forecast vs Previous values

### 📈 IPO Tracker
- Upcoming and listed IPOs from NASDAQ & NYSE
- Company name, symbol, exchange, price range
- Listing date and status (Upcoming / Listed / New Filing)

### 🔔 Push Notifications
- High & Critical impact event alerts
- Custom notification channel with vibration & LED
- Auto-start on device boot via BootReceiver

### 🏷️ Topic Filtering
- Trump / White House announcements
- Federal Reserve (Fed / FOMC / Interest Rate)
- War & Geopolitical conflicts
- Inflation & CPI data
- Gold / XAU price movements
- Oil / Crude prices
- DXY / US Dollar Index
- NASDAQ / SPX / Dow Jones indices
- IPO listings & filings

### ⭐ Favorites & Read Tracking
- Mark articles as favorite for quick access
- Mark articles as read to track your progress
- Room database persistence (works offline)

### 🎨 Professional Theme
- Material Components Design System
- Dark/Light theme support
- Custom color palette (AlertNews Red #FF3B30, Blue Accent #007AFF)
- Status bar auto-color matching theme
- Impact color-coded badges

---

## Project Structure

```
alertnews/
├── app/
│   ├── build.gradle
│   ├── proguard-rules.pro
│   └── src/
│       ├── main/
│       │   ├── AndroidManifest.xml
│       │   ├── java/com/alertnews/app/
│       │   │   ├── MainActivity.kt
│       │   │   ├── MainApplication.kt
│       │   │   ├── NotificationHelper.kt
│       │   │   ├── NotificationService.kt
│       │   │   ├── BootReceiver.kt
│       │   │   ├── TopicsProvider.kt
│       │   │   ├── FilterManager.kt
│       │   │   ├── adapters/
│       │   │   │   ├── NewsAdapter.kt
│       │   │   │   ├── CalendarAdapter.kt
│       │   │   │   ├── IpoAdapter.kt
│       │   │   │   └── DiffCallback.kt
│       │   │   ├── data/
│       │   │   │   ├── model/
│       │   │   │   │   ├── NewsItem.kt
│       │   │   │   │   ├── EconomicCalendar.kt
│       │   │   │   │   └── IpoEvent.kt
│       │   │   │   ├── local/
│       │   │   │   │   ├── NewsDatabase.kt
│       │   │   │   │   └── NewsDao.kt
│       │   │   │   ├── repository/
│       │   │   │   │   └── NewsRepository.kt
│       │   │   │   └── viewmodels/
│       │   │   │       └── NewsViewModel.kt
│       │   │   └── ui/
│       │   │       ├── CalendarFragment.kt
│       │   │       └── IpoFragment.kt
│       │   └── res/
│       │       ├── layout/
│       │       │   ├── activity_main.xml
│       │       │   ├── item_news.xml
│       │       │   ├── item_calendar.xml
│       │       │   └── item_ipo.xml
│       │       ├── values/
│       │       │   ├── colors.xml
│       │       │   ├── themes.xml
│       │       │   └── strings.xml
│       │       └── mipmap/
│
├── build.gradle
├── settings.gradle
├── gradle.properties
├── CHANGELOG.md
├── README.md
└── setup-github.sh
```

---

## Setup & Installation

### Prerequisites
- Android Studio (latest stable)
- Android SDK 34+ (compileSdk 34)
- JDK 17+
- Kotlin 1.9.22+

### Clone & Build

```bash
# Clone the repository
git clone https://github.com/ImranDev3/alertnews.git

# Navigate to project directory
cd alertnews

# Open in Android Studio
# OR build via command line:
./gradlew assembleDebug
```

### APK Output
```
app/build/outputs/apk/debug/app-debug.apk
```

### Run on Device
1. Connect your Android device via USB
2. Enable USB Debugging
3. Run: `./gradlew installDebug`

---

## Technology Stack

| Component | Technology | Version |
|-----------|-----------|---------|
| Language | Kotlin | 1.9.22 |
| UI Framework | Jetpack Material Components | 1.12.0 |
| Database | Room | 2.6.1 |
| Network | Retrofit + OkHttp | 2.11.0 / 4.12.0 |
| Image Loading | Glide | 4.16.0 |
| Background Work | WorkManager | 2.9.0 |
| Coroutines | kotlinx-coroutines | 1.8.1 |
| JSON Parser | Gson | 2.10.1 |
| Min SDK | Android | 26 |
| Target SDK | Android | 34 |

---

## GitHub Push Commands

### First-time setup
```bash
# Navigate to project directory
cd C:/Users/imran/Desktop/app

# Initialize git repository
git init

# Configure git user (set once)
git config user.name "ImranDev3"
git config user.email "imrandev3@users.noreply.github.com"

# Add all files to staging
git add .

# Commit with professional message
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

### For future updates
```bash
git add .
git commit -m "feat: your descriptive commit message"
git push origin main
```

---

## Permissions

| Permission | Purpose |
|------------|---------|
| INTERNET | Fetch news from RSS feeds and APIs |
| POST_NOTIFICATIONS | Push alerts for high-impact events |
| WAKE_LOCK | Keep device awake during news fetch |
| RECEIVE_BOOT_COMPLETED | Auto-start after device reboot |

---

## Contributing

Contributions are welcome! Please follow these steps:

1. **Fork** the repository
2. **Create** a feature branch: `git checkout -b feature/your-feature`
3. **Commit** your changes: `git commit -m "feat: description"`
4. **Push** to branch: `git push origin feature/your-feature`
5. **Open** a Pull Request

---

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.

---

## Contact

**Developer:** ImranDev3  
**Email:** imrandev3@users.noreply.github.com  
**GitHub:** https://github.com/ImranDev3  
**Repository:** https://github.com/ImranDev3/alertnews

---

**AlertNews** — Real-time financial news for forex & index traders.  
*Free & Open Source.*  
Co-Authored-By: Claude Code <noreply@anthropic.com>
