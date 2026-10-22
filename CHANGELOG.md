# Changelog

All notable changes to the AlertNews project will be documented in this file.

The format is based on [Keep a Changelog](https://keepachangelog.com/en/1.0.0/),
and this project adheres to [Semantic Versioning](https://semver.org/spec/v2.0.0.html).

## [1.0.0] - 2024-10-22

### Added - PHASE 1: Build Configuration
- Top-level build.gradle with Kotlin plugin configuration
- settings.gradle with module definition and repository management
- gradle.properties with JVM arguments and caching enabled
- app/build.gradle with Android configuration (compileSdk 34, Kotlin 17)
- All dependencies: Room, Retrofit, OkHttp, Glide, WorkManager, Navigation
- proguard-rules.pro with library keep rules

### Added - PHASE 2: Data Models
- NewsItem.kt: Room entity with full field mapping (title, summary, source, url, imageUrl, publishedAt, impactLevel, symbol, currency, category, eventType, topics, isRead, isFavorite)
- EconomicCalendar.kt: Forecast/actual fields with date parsing and impact level tracking
- IpoEvent.kt: Company name, symbol, exchange, price range, listing status
- DiffCallback implementations for RecyclerView optimization

### Added - PHASE 3: Data Layer
- NewsDatabase.kt: Room database with two entities (news_items, calendar_events)
- NewsDao.kt: Full SQL queries for CRUD operations, filters, favorites, recent news
- NewsDataSource interface: Pattern for remote feed integration
- RssNewsSource.kt: RSS/Atom feed parsing from Investing.com, DailyFX, Reuters, MarketWatch
- FactoryCalendarSource.kt: Forex Factory weekly calendar JSON parsing
- IpoNewsSource.kt: NASDAQ/NYSE IPO tracking with fallback data

### Added - PHASE 4: Repository & ViewModel
- NewsRepository.kt: StateFlow for live updates, auto-refresh on creation
- NewsViewModel.kt: AndroidViewModel with coroutine scope

### Added - PHASE 5: UI Components
- MainActivity.kt: RecyclerView with swipe refresh, FAB add source
- NewsAdapter.kt: ViewBinding, impact badges, symbol tags, favorite toggle
- CalendarAdapter.kt: Economic event items with color-coded impact
- IpoAdapter.kt: IPO cards with price range display
- Layout files: activity_main.xml, item_news.xml, item_calendar.xml, item_ipo.xml

### Added - PHASE 6: Resources
- colors.xml: AlertNews red (#FF3B30), blue accent (#007AFF), neutral tones
- themes.xml: MaterialComponents.DayNight.NoActionBar with status bar auto-color
- strings.xml: All user-facing strings
- AndroidManifest.xml: Permissions (INTERNET, POST_NOTIFICATIONS, WAKE_LOCK, RECEIVE_BOOT_COMPLETED)

### Added - PHASE 7: Theme & UI Polish
- Full dark/light theme support via MaterialComponents
- Impact level badges: critical (red), high (orange), medium (yellow), low (green)
- TopicsProvider.kt: Trump, Fed, Inflation, Conflict, Gold, Oil, DXY, NASDAQ, IPO tracking
- FilterManager.kt: User preferences persistence
- NotificationHelper.kt: Push notifications with channels, lights, vibration
- BootReceiver.kt: Auto-start after device reboot

### Performance
- Uses Room with indices for fast queries
- StateFlow for reactive UI updates
- OkHttp with 15s timeouts
- Glide for efficient image loading

---

## [Unreleased]

### Planned
- [ ] Firebase Cloud Messaging for push notifications
- [ ] Widget support
- [ ] Dark mode toggle in settings
- [ ] Custom RSS feed manager
- [ ] Email alert subscription
- [ ] Historical news archive

---

## GitHub Push Commands (Step by Step)

```bash
# Initialize git repository (if not already)
git init

# Configure Git user (only needed once per computer)
git config user.name "ImranDev3"
git config user.email "imrandev3@users.noreply.github.com"

# Add all files to staging area
git add .

# Commit with descriptive message
git commit -m "feat: full project - add all source files, UI, theme, notifications

- Add build.gradle, settings.gradle, gradle.properties
- Add Data models: NewsItem, EconomicCalendar, IpoEvent
- Add Data layer: Repository, DAO, DataSources (RSS, Calendar, IPO)
- Add UI: Activity, Adapters, Layout files
- Add Resources: colors, themes, strings
- Add Notifications: Helper, Service, BootReceiver
- Add Theme system with dark/light support
- Add TopicsProvider and FilterManager

Co-Authored-By: Claude Code <noreply@anthropic.com>"

# Set main branch as default
git branch -M main

# Add GitHub remote (if not already added)
git remote add origin https://github.com/ImranDev3/alertnews.git

# Push to GitHub
git push -u origin main

# Verify remote
git remote -v
git log --oneline -n 3
```