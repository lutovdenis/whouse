# whouse


Implemented features:

1) Getting the list of inventories (GET request, can be tested with curl: curl http://localhost:8080/products)
2) Adding new inventories (POST request, can be tests with curl: curl -X POST http://localhost:8080/inventories -H "Content-Type: application/json" -d "@Path\to\inventory.json"
3) Getting the list of currently available products based on inventories(GET request, can be tested with curl:curl http://localhost:8080/products)

Solution is as following:
	REST API built on Spring Boot with H2 in-memory database for demo purposes.
	RestController feature is used for handling REST requests
	JPA is used for mapping entities to DB.

Project structure:
	Entites definitions are described in MODEL package.
	Access to Entites (for other parts of the application)is provided via REPOSITORIES package
	API logic is incorporated in RESTCONTROLLERS package
	What to improve:
		Mapping JSONs to Entity objects may be implemented in serapate package but not in RESTCONTROLLERS
	The other way to group classes into packages could be based on Entites affilation, but not application layers.

Algorithm for retrieving available products:
	In short available products are evaluated like following:
		1) Get in memory all products definitions
		2) Get in memory all available inventories
		3) Loop through product definitions 
			  and on every iteration check if left inventories are enough to assemble the product 
			  and if so, then decrease left inventories and increment available products.
		   Continue such loop over and over again until no product can be added.
		   This approarch is not optimal!
    What to improve:
		Better to define a strategy telling if some kind of products have priority against the other 
		and should be assembled first (Different products seek for same kind of inventories!)
		And also it is important to define if there should be one kind of product being assembled as much as inventory allows it
		before assembling any other kind of product.
		And then define an algo accordingly.
Data model:
	Product implemented in such way that it`s relation to Inventory in the code is implicit.
	That on the one hand saved some simplicity - no need to code that dependency explicitely (Many to many relation in JPA)
	and probably saved a bit of performance (no DB constrains)
	On the other hand - Data integrity is worse as it is not guaranteed (as it would be when explicitely reflected in DB)
    and it is the responsibity of app code to perform integrity checks (for instance - ART_ID used in certain product is valid and defined in inventories)	

