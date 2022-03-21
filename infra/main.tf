provider "azurerm" {
    features {}
}

resource "azurerm_container_registry" "acr01" {
  name                     = "acr-shop-01"
  resource_group_name      = "research-log-analytics"
  location                 = "West Europe"
  sku                      = "Basic"
  admin_enabled            = false
}


resource "azurerm_kubernetes_cluster" "aks01" {
  name                = "aks-shop-01"
  kubernetes_version  = "1.23.3"
  location            = "West Europe"
  resource_group_name = "research-log-analytics"
  dns_prefix          = "aks-shop-01"

  default_node_pool {
    name                = "system"
    node_count          = "1"
    vm_size             = "Standard_DS2_v2"
    type                = "VirtualMachineScaleSets"
    availability_zones  = [1, 2, 3]
    enable_auto_scaling = false
  }

  identity {
    type = "SystemAssigned"
  }

  network_profile {
    load_balancer_sku = "Standard"
    network_plugin    = "kubenet"
  }
}


resource "azurerm_application_insights" "appinsights01" {
  name                = "appinsights-shop-01"
  location            = "West Europe"
  resource_group_name = "research-log-analytics"
  application_type    = "web"
}
