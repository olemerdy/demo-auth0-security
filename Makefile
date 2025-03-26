build: ## Run the build
	@echo "Running the Gradle build, including test suite"
	@cd backend-spring && gradle build
	@echo "Running the NPM build"
	@cd frontend-angular && npm run build

clean: ## Clean the build files
	@echo "Cleaning the build files"
	@cd backend-spring && gradle clean

help:
	@grep -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-30s\033[0m %s\n", $$1, $$2}'

.PHONY: build clean help
.DEFAULT_GOAL := help