# 1- Create Github personal access token from here https://github.com/settings/tokens/new

# 2 Configure your CI

# ********************************* For Travis CI *********************************
# Use the next command to add the Github personal access token as encrypted global variable
# $ travis encrypt GITHUB_SECRET_TOKEN="Github Personal access token" --add
# You have to see the next lines in the job log
# Setting environment variables from .travis.yml
# $ export GITHUB_SECRET_TOKEN=[secure]

# Set the request params
GITHUB_TOKEN=$GITHUB_SECRET_TOKEN
PULL_REQUEST_ID=$TRAVIS_PULL_REQUEST
OWNER_NAME="tarek360"
REPO_NAME="emptyapp2"
COMMIT_TITLE=""
COMMIT_MESSAGE=""

url="https://api.github.com/repos/$OWNER_NAME/$REPO_NAME/pulls/$PULL_REQUEST_ID/merge"

echo "Merging.. pull request https://github.com/$OWNER_NAME/$REPO_NAME/pull/$PULL_REQUEST_ID"


if [ -z "$COMMIT_TITLE" ]
then
      # merge with default COMMIT_TITLE & COMMIT_MESSAGE if the COMMIT_MESSAGE is Empty
      curl -H 'Host: api.github.com' -H 'Content-Type: application/json' -H "Authorization: token $GITHUB_TOKEN" -X PUT --compressed $url
else
      # merge with COMMIT_TITLE & COMMIT_MESSAGE if the COMMIT_MESSAGE is Not Empty
      curl -H 'Host: api.github.com' -H 'Content-Type: application/json' -H "Authorization: token $GITHUB_TOKEN" --data-binary "{\"commit_title\":\"${COMMIT_TITLE}\", \"commit_message\":\"${COMMIT_MESSAGE}\"}" -X PUT --compressed $url
fi
