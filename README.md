
This project is created as Python project (using `graalpython),
then converted into Java project by adding maven pom.xml


Create new graalpython project:

    mkdir dirname && cd dirname
    graalpython -m venv venv
    source venv/bin/activate

To see list of supported packages:

    graalpython -m ginstall install --help

Install `requests`
    
    graalpython -m ginstall install requests

Document dependencies

    pip freeze > req.txt

Run (using `graalpython` actually):

    python health.py 

Docs
- https://www.graalvm.org/python/
- https://www.graalvm.org/reference-manual/python/
- https://www.graalvm.org/reference-manual/python/Packages/

