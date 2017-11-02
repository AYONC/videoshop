docker-build-base-videoshop-image:
	docker build -f docker/base-videoshop/Dockerfile -t base-videoshop .

docker-build-ci-base-image:
	docker build -f docker/ci-base/Dockerfile -t ci-base .

docker-build-videoshop-image:
	docker build -f docker/videoshop/Dockerfile -t videoshop --build-arg version=0.0.1 .
