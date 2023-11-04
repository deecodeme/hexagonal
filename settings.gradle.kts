rootProject.name = "hexagonal"
include("ddd")
include("city")
include("city:adapter")
include("city:ddd")
include("city:application")
include("city:web-adapter")
include("city:persistence-adapter")
include("city:external-adapter")
include("city:api")
include("city:temp")
include("city:configuration")
include("city:domain")
include("order")
include("order:api")
include("order:domain")
include("account")
include("inventory")
include("order:application")
findProject(":order:application")?.name = "application"
include("order:persistence-adapter")
findProject(":order:persistence-adapter")?.name = "persistence-adapter"
include("lang")
include("account:domain")
findProject(":account:domain")?.name = "domain"
include("account:application")
findProject(":account:application")?.name = "application"
include("account:adapter")
findProject(":account:adapter")?.name = "adapter"
include("account:configuration")
findProject(":account:configuration")?.name = "configuration"
