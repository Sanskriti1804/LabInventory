# LabInventory 📱

A modern Android application for managing laboratory equipment inventory, bookings, and facility management built with Jetpack Compose and Supabase.

## 🚀 Features

### Core Functionality
- **Equipment Management**: Browse, search, and manage laboratory equipment
- **Category-based Organization**: Equipment organized by categories and subcategories
- **Booking System**: Reserve equipment with approval workflow
- **Calendar Integration**: View and manage equipment bookings
- **Real-time Chat**: AI-powered chat support for users
- **Responsive Design**: Adaptive UI for different screen sizes

### User Roles
- **Regular Users**: Browse equipment, make bookings, chat with AI
- **Administrators**: Manage equipment, approve bookings, oversee inventory

### Technical Features
- **Modern UI**: Built with Material Design 3 and Jetpack Compose
- **Offline Support**: Local data caching and offline functionality
- **Real-time Sync**: Supabase backend for real-time data synchronization
- **Search & Filtering**: Advanced search with category and facility filters
- **Image Management**: Equipment image handling and storage

## 🏗️ Architecture

### Tech Stack
- **Frontend**: Jetpack Compose, Material Design 3
- **Backend**: Supabase (PostgreSQL + Real-time)
- **Dependency Injection**: Koin
- **Navigation**: Jetpack Navigation Compose
- **Image Loading**: Coil
- **Serialization**: Kotlinx Serialization
- **HTTP Client**: Ktor

### Project Structure
```
app/src/main/java/com/example/labinventory/
├── data/                    # Data layer
│   ├── model/              # Data models and entities
│   ├── remote/             # API interfaces and services
│   └── schema/             # Database schemas
├── di/                     # Dependency injection modules
├── navigation/             # Navigation components
├── repository/             # Data repositories
├── ui/                     # UI components and screens
│   ├── components/         # Reusable UI components
│   ├── screens/            # Main application screens
│   └── theme/              # App theming and styling
├── util/                   # Utility classes and helpers
├── viewmodel/              # ViewModels for state management
└── LabInventoryApp.kt      # Application class
```

### Key Components
- **MVVM Architecture**: ViewModels manage UI state and business logic
- **Repository Pattern**: Centralized data access through repositories
- **Composable UI**: Declarative UI built with Jetpack Compose
- **Bottom Navigation**: Tab-based navigation between main sections

## 📱 Screens

1. **Home Screen**: Equipment categories and search
2. **Equipment Screen**: Equipment listings with filters
3. **Product Description**: Detailed equipment information
4. **Booking Screen**: Equipment reservation management
5. **Calendar Screen**: Booking calendar view
6. **Chat Screen**: AI-powered support chat
7. **Project Info**: Project information and details

## 🛠️ Setup Instructions

### Prerequisites
- Android Studio Hedgehog or later
- Android SDK 35 (API level 35)
- Minimum SDK: 24 (Android 7.0)
- Java 11 or later
- Kotlin 1.9.0+

### Installation

1. **Clone the repository**
   ```bash
   git clone <repository-url>
   cd LabInventory
   ```

2. **Configure Supabase**
   - Create a Supabase project at [supabase.com](https://supabase.com)
   - Get your project URL and anon key
   - Create a `local.properties` file in the root directory:
   ```properties
   SUPABASE_URL=your_supabase_project_url
   SUPABASE_KEY=your_supabase_anon_key
   ```

3. **Build and Run**
   ```bash
   ./gradlew build
   ./gradlew installDebug
   ```

### Database Setup

The application uses the following Supabase tables:
- `items`: Equipment inventory
- `item_categories`: Equipment categories
- `item_sub_categories`: Equipment subcategories
- `branches`: Laboratory branches
- `departments`: Department information
- `facilities`: Facility details
- `bookings`: Equipment reservations
- `users`: User management

## 🔧 Configuration

### Build Configuration
- **Compile SDK**: 35
- **Target SDK**: 35
- **Min SDK**: 24
- **Version Code**: 1
- **Version Name**: 1.0

### Dependencies
- **Jetpack Compose BOM**: Latest stable version
- **Supabase**: 2.6.0 BOM
- **Koin**: 3.5.3 for dependency injection
- **Navigation Compose**: 2.7.7
- **Coil**: 2.5.0 for image loading

## 🚀 Usage

### For Users
1. **Browse Equipment**: Navigate through equipment categories
2. **Search & Filter**: Use search bar and filters to find specific equipment
3. **Make Bookings**: Reserve equipment for specific time slots
4. **View Calendar**: Check equipment availability and your bookings
5. **Get Support**: Use AI chat for assistance

### For Administrators
1. **Manage Inventory**: Add, edit, and remove equipment
2. **Approve Bookings**: Review and approve equipment reservations
3. **Monitor Usage**: Track equipment utilization and maintenance
4. **User Management**: Manage user accounts and permissions

## 🧪 Testing

### Unit Tests
```bash
./gradlew test
```

### Instrumented Tests
```bash
./gradlew connectedAndroidTest
```

## 📦 Build Variants

- **Debug**: Development build with debugging enabled
- **Release**: Production build with ProGuard optimization

## 🔒 Security

- Supabase Row Level Security (RLS) for data access control
- Secure API key management through BuildConfig
- User role-based access control
- Input validation and sanitization

## 🌐 API Endpoints

The application integrates with Supabase APIs:
- **Items API**: Equipment CRUD operations
- **Categories API**: Category management
- **Bookings API**: Reservation management
- **User API**: Authentication and user management
- **Facilities API**: Facility information

## 📱 Responsive Design

- Adaptive layouts for different screen sizes
- Material Design 3 components
- Dark/Light theme support
- Edge-to-edge display support


## 🔄 Changelog

### Version 1.0
- Initial release
- Core equipment management features
- Booking system
- AI chat support
- Responsive UI design

---

**Built with ❤️ using modern Android development practices**
