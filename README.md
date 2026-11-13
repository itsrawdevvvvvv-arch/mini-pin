# PinLite (Custom Pinterest Client)

A distraction-free, 100% ad-free Pinterest client built with Jetpack Compose, featuring:
- **Home Feed:** Infinite masonry visual grid with zero sponsored cards and click-to-play video controls.
- **Visual Search:** Instant keyword search with Pinterest-grade image results.
- **Mood Boards & Collages:** Custom board creation, saved pins organizer, and moodboard management.
- **Zero-Login:** Persistent local session with pre-configured boards (no signup or login screens).
- **Design System:** Electric neon lime accents (`#D4FF00`) on deep charcoal surfaces (`#0D0E11`), floating pill navigation dock, and bento card metrics.

---

## How to Compile into a Debug APK

### Option 1: Using Command Line (Gradle)
Run the following in the project root:

```bash
./gradlew assembleDebug
```
(On Windows PowerShell / CMD):
```powershell
.\gradlew.bat assembleDebug
```

The compiled APK will be generated at:
```
app/build/outputs/apk/debug/app-debug.apk
```

### Option 2: Using Android Studio
1. Open Android Studio.
2. Select **File > Open** and select this directory (`pinlite-android-source`).
3. Allow Gradle to sync.
4. Click **Build > Build Bundle(s) / APK(s) > Build APK(s)** or click the **Run** button to install directly onto a connected device or emulator.
