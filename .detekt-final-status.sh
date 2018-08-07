SHA=$(git rev-parse HEAD)
PRE_LAST_SHA=$(git rev-parse @~)
BRANCH=$(git rev-parse --abbrev-ref HEAD)

TOKEN=$GITHUB_TOKEN
PULL_REQUEST_ID=$TRAVIS_PULL_REQUEST
OWNER_NAME="tarek360"
REPO_NAME="emptyapp2"

REPORT_DATA=$(<reports/detekt/output/detekt-plain.txt)

if [ -z "$REPORT_DATA" ]
then
  STATE="success"
  DESCRIPTION="Build Passed"
  TARGET_URL=""
else
  bash .detekt-report.sh
  STATE="failure"
  DESCRIPTION="Check the report in the comment"
  TARGET_URL=""
fi

CONTEXT="Detekt"

url="https://api.github.com/repos/$OWNER_NAME/$REPO_NAME/statuses/$SHA"

echo "Create status.. for SHA: $SHA in PR: $PULL_REQUEST_ID"

# Create status
curl -H 'Host: api.github.com' -H 'Content-Type: application/json' -H "Authorization: token $TOKEN" --data-binary "{\"state\":\"${STATE}\", \"target_url\":\"${TARGET_URL}\", \"description\":\"${DESCRIPTION}\", \"context\":\"${CONTEXT}\"}" --compressed $url

