all:
	ant -buildfile Compiler/build.xml build

test:

	./scripts/ml_arm.sh
	./scripts/output1.sh
	./scripts/compare.sh

clean :
	ant -buildfile Compiler/build.xml clean

