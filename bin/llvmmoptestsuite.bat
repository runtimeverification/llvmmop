@echo off

set SRC_ROOT=%~dp0..

java -cp "%SRC_ROOT%\lib\rvmonitor.jar;%SRC_ROOT%\lib\rt.jar;%SRC_ROOT%/lib/llvmmoptestsuite.jar;%SRC_ROOT%/lib/llvmmop.jar;%SRC_ROOT%/lib/logicrepository.jar:%SRC_ROOT%/lib/*.jar" llvmmoptestsuite.Main -local "%SRC_ROOT%/examples" %*

