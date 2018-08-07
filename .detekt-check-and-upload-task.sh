SHA=$(git rev-parse HEAD)
TOKEN=$GITHUB_TOKEN

./gradlew detektPendingStatus "-Pargs=$TOKEN,$SHA"
./gradlew detektCheck
./gradlew reportDetekt "-Pargs=$TOKEN,$SHA"
