# AlertNews - Android Forex & Index News Alert App

## Overview

AlertNews is a free and open-source Android application for forex and index traders. Get real-time news alerts, track economic calendar events, monitor IPO launches, and stay on top of market sessions with a beautiful modern UI.

## Features

### News & Alerts
- Real-time breaking news for forex & index traders
- Push notifications for high-impact economic events
- Source aggregation: RSS feeds from Investing.com, DailyFX, Reuters, MarketWatch
- Forex Factory calendar integration
- NASDAQ IPO tracking
- Topic filtering A-Z (18 topics including Trump, Fed, War, Inflation/CPI, NFP/Jobs, GDP, Gold/XAU, Oil/Crude, DXY/Dollar, NASDAQ, SPX/S&P500, Dow Jones, IPO, Central Banks, Economy, Politics, Crypto, Market Crash, Bailout/Stimulus, China)

### Economic Calendar
- Forex Factory style economic calendar
- Real-time event data with actual/forecast/previous values
- Price impact indicators (Critical/High/Medium/Low)
- Filter by currency and impact level
- Countdown timer until event

### Market Sessions
- Trading session times: Sydney, Tokyo, London, New York, Los Angeles
- Session status display (active/inactive)
- Market hours in user's timezone

### User Settings
- Enable/disable push notifications
- High impact alerts only filter
- Custom topic filtering with checkboxes A-Z
- Dark mode / Light mode toggle
- Background music with volume control
- Custom music URL support
- Symbol filter (comma-separated)
- Currency pair filter
- Favorites-only mode
- Auto-refresh interval (minutes)
- Timezone selection

### Notifications
- Custom notification channels with priority levels
- Impact-based notification importance (Critical/High/Medium/Low)
- Foreground service for persistent alerts
- Auto-start after device reboot

### Music Player
- Optional background ambient music
- Volume control seek bar
- Enable/disable toggle
- Custom music URL input
- Persist settings across app launches

## Project Structure

```
alertnews/
├── app/
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/com/alertnews/app/
│   │   │   │   ├── MainActivity.kt
│   │   │   │   ├── NewsViewModel.kt
│   │   │   │   ├── MainApplication.kt
│   │   │   │   ├── MusicPlayer.kt
│   │   │   │   ├── NotificationHelper.kt
│   │   │   │   ├── NotificationService.kt
│   │   │   │   ├── BootReceiver.kt
│   │   │   │   ├── SettingsFragment.kt
│   │   │   │   ├── TopicsProvider.kt
│   │   │   │   ├── FilterManager.kt
│   │   │   │   ├── ui/
│   │   │   │   │   ├── CalendarFragment.kt
│   │   │   │   │   ├── IpoFragment.kt
│   │   │   │   │   ├── TopicAdapter.kt
│   │   │   │   │   ├── NewsAdapter.kt
│   │   │   │   │   ├── CalendarAdapter.kt
│   │   │   │   │   ├── IpoAdapter.kt
│   │   │   │   │   └── DiffCallback.kt
│   │   │   │   ├── data/
│   │   │   │   │   ├── model/
│   │   │   │   │   │   ├── NewsItem.kt
│   │   │   │   │   │   ├── EconomicCalendar.kt
│   │   │   │   │   │   ├── ForecastEvent.kt
│   │   │   │   │   │   ├── MarketSession.kt
│   │   │   │   │   │   └── UserSettings.kt
│   │   │   │   │   ├── repository/
│   │   │   │   │   │   └── NewsRepository.kt
│   │   │   │   │   ├── source/
│   │   │   │   │   │   ├── NewsDataSource.kt
│   │   │   │   │   │   ├── RssNewsSource.kt
│   │   │   │   │   │   ├── FactoryCalendarSource.kt
│   │   │   │   │   │   └── IpoNewsSource.kt
│   │   │   │   │   └── local/
│   │   │   │   │       ├── NewsDatabase.kt
│   │   │   │   │       └── NewsDao.kt
│   │   │   │   └── util/
│   │   │   │       └── TimezoneUtil.kt
│   │   │   └── res/
│   │       ├── layout/
│   │       │   ├── activity_main.xml
│       │       ├── fragment_settings.xml
│       │       ├── fragment_calendar.xml
│       │       ├── fragment_ipo.xml
│       │       ├── item_news.xml
│       │       ├── item_calendar.xml
│       │       ├── item_ipo.xml
│       │       └── item_topic.xml
│       ├── values/
│       │   ├── colors.xml
│       │   ├── themes.xml
│       │   └── strings.xml
│       └── drawable/
│           ├── ic_star_filled.xml
│           ├── ic_star_outline.xml
│           ├── ic_add.xml
│           ├── ic_notification.xml
│           ├── ic_music_note.xml
│           └── ic_music.xml
├── build.gradle
├── settings.gradle
└── gradle.properties
```

## Technology Stack

- **Language**: Kotlin 1.9.22
- **IDE**: Android Studio
- **Build System**: Gradle (compileSdk 34, minSdk 26, targetSdk 34)
- **Architecture**: MVVM with Jetpack components
- **UI**: Jetpack Compose (Material Components DayNight theme)
- **Database**: Room
- **Networking**: Retrofit + OkHttp
- **Image Loading**: Glide
- **Background Work**: WorkManager, Coroutines
- **Navigation**: Jetpack Navigation Component
- **Dependency Injection**: Manual (Application class)
- **Notifications**: NotificationManager with custom channels
- **Timezone Handling**: Custom TimezoneUtil

## Git Setup

```bash
git init
git config user.name "ImranDev3"
git config user.email "dev@alertnews.app"
git add .
git commit -m "Initial commit: AlertNews professional setup"
git branch -M main
git remote add origin https://github.com/ImranDev3/alertnews.git
git push -u origin main
```

## Development Progress

This project is built incrementally with detailed commit messages following the conventional commit format. Each phase adds new functionality while maintaining backward compatibility.

### Recent Changes

- **Phase 1**: Project setup, Gradle configuration, initial README
- **Phase 2**: News model, Room database, NewsDao, basic RSS fetching
- **Phase 3**: Forex Factory calendar, IPO tracking, Topic system
- **Phase 4**: Market sessions, Timezone support, Notification system
- **Phase 5**: Music player, User settings, FilterManager
- **Phase 6**: Settings Fragment, topic filtering, timezone selector, drawable resources

## Contributing

This is a free and open-source project. Contributions are welcome! Please see the issue tracker on GitHub for feature requests and bug reports.

## License

MIT License - Free for personal and commercial use.

## Contact

- Email: dev@alertnews.app
- GitHub: https://github.com/ImranDev3/alertnews