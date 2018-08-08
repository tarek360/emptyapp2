setup_git() {
  git config --global user.email "$bot_email"
  git config --global user.name "louga-bot"
}

commit_website_files() {
  git checkout -b gh-pages
  mkdir ./reports/jacoco/${TRAVIS_BUILD_NUMBER}
  cp -R ./build/reports/jacoco/jacocoFullReport ./reports/jacoco/${TRAVIS_BUILD_NUMBER}
  git add -- reports/jacoco/${TRAVIS_BUILD_NUMBER}
  git commit --message "jacoco report, Travis build: ${TRAVIS_BUILD_NUMBER}"
}

upload_files() {
  git remote add origin-pages https://${GITHUB_API_TOKEN}@github.com/tarek360/emptyapp2.git > /dev/null 2>&1
  git push --quiet --set-upstream origin-pages gh-pages 
}

setup_git
commit_website_files
upload_files
