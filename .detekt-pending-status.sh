SHA=$(git rev-parse HEAD)

TOKEN=$GITHUB_TOKEN
PULL_REQUEST_ID=$TRAVIS_PULL_REQUEST
OWNER_NAME="tarek360"
REPO_NAME="emptyapp2"

STATE="pending"
DESCRIPTION="Checking.."
TARGET_URL=""
CONTEXT="Detekt"

url="https://api.github.com/repos/$OWNER_NAME/$REPO_NAME/statuses/$SHA"

# Create status
echo "Create pending status.. for SHA: $SHA in PR: $PULL_REQUEST_ID"
curl -H 'Host: api.github.com' -H 'Content-Type: application/json' -H "Authorization: token $TOKEN" --data-binary "{\"state\":\"${STATE}\", \"target_url\":\"${TARGET_URL}\", \"description\":\"${DESCRIPTION}\", \"context\":\"${CONTEXT}\"}" --compressed $url
