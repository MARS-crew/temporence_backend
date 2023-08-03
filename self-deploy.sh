docker buildx build --platform linux/arm64,linux/amd64 -t pinomaker/mars-game:latest-arm64 -t pinomaker/mars-game:latest-amd64 --file Dockerfile.local .  --push
