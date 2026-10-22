#!/usr/bin/env bash
# ============================================================
# AlertNews — GitHub Repo Setup Script (Professional Workflow)
# Run this script from: c:/Users/imran/Desktop/app
# ============================================================
set -e

REPO_URL="https://github.com/ImranDev3/alertnews.git"
APP_DIR="c:/Users/imran/Desktop/app"

echo "============================================================"
echo "  AlertNews — GitHub Setup (Professional Way)"
echo "============================================================"

# 1. Check git is installed
if ! command -v git &> /dev/null; then
    echo "[ERROR] Git is not installed. Please install Git first."
    exit 1
fi

# 2. Change to app directory
cd "$APP_DIR" || { echo "[ERROR] Cannot enter $APP_DIR"; exit 1; }

# 3. Initialize git repository (if not already)
if [ ! -d ".git" ]; then
    echo "[1/7] Initializing Git repository..."
    git init
else
    echo "[1/7] Git repository already exists."
fi

# 4. Set default branch to main
echo "[2/7] Setting default branch to 'main'..."
git branch -M main

# 5. Configure user (optional — set if not set)
if [ -z "$(git config user.name)" ]; then
    echo "[3/7] Configuring Git user (set your name/email)..."
    git config user.name "ImranDev3"
    git config user.email "imrandev3@users.noreply.github.com"
else
    echo "[3/7] Git user already configured."
fi

# 6. Stage all files
echo "[4/7] Staging all project files..."
git add .

# 7. Commit
echo "[5/7] Creating initial commit..."
if git commit -m "first commit: AlertNews professional setup

- Add README.md project documentation
- Add build.gradle (Gradle project setup)
- Add settings.gradle (module configuration)
- Co-Authored-By: Claude Code <noreply@anthropic.com>" ; then
    echo "[OK] Commit created."
else
    echo "[INFO] Nothing to commit (working tree clean)."
fi

# 8. Add remote origin (skip if already added)
echo "[6/7] Adding GitHub remote origin..."
if ! git remote get-url origin &> /dev/null; then
    git remote add origin "$REPO_URL"
    echo "[OK] Remote origin added: $REPO_URL"
else
    echo "[INFO] Remote origin already exists."
fi

# 9. Push to GitHub
echo "[7/7] Pushing to GitHub..."
git push -u origin main

echo "============================================================"
echo "[SUCCESS] AlertNews pushed to GitHub!"
echo "Repo: $REPO_URL"
echo "============================================================"