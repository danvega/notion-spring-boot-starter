#!/bin/bash

# This script runs the sample application for manual testing

# Check for Notion API key
if [ -z "$NOTION_API_KEY" ]; then
  echo "Error: NOTION_API_KEY environment variable is required"
  echo "Please set it with: export NOTION_API_KEY=your_notion_api_key"
  exit 1
fi

# Build the project first
echo "Building project..."
mvn clean package -DskipTests

# Run the sample application
echo "Running sample application..."
mvn spring-boot:run \
  -Dspring-boot.run.profiles=sample \
  -Dspring-boot.run.main-class=dev.danvega.notion.sample.SampleNotionApplication