docker-build-base-videoshop-image:
	docker build -f docker/base-videoshop/Dockerfile -t base-videoshop .

docker-build-videoshop-image:
	docker build -f docker/videoshop/Dockerfile -t videoshop --build-arg version=0.0.1 .

