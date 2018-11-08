package com.github.nicholashauschild.cfg

/**
 * Author: nicholas.hauschild
 */

// EC2
fun ec2(suffix : String) : String = "AWS::EC2::$suffix"

val ec2_customerGateway                   = ec2("CustomerGateway")
val ec2_dhcpOptions                       = ec2("DHCPOptions")
val ec2_egressOnlyInternetGateway         = ec2("EgressOnlyInternetGateway")
val ec2_eip                               = ec2("EIP")
val ec2_eipAssociation                    = ec2("EIPAssociation")
val ec2_flowLog                           = ec2("FlowLog")
val ec2_host                              = ec2("Host")
val ec2_instance                          = ec2("Instance")
val ec2_internetGateway                   = ec2("InternetGateway")
val ec2_launchTemplate                    = ec2("LaunchTemplate")
val ec2_natGateway                        = ec2("NatGateway")
val ec2_networkAcl                        = ec2("NetworkAcl")
val ec2_networkAclEntry                   = ec2("NetworkAclEntry")
val ec2_networkInterface                  = ec2("NetworkInterface")
val ec2_networkInterfaceAttachment        = ec2("NetworkInterfaceAttachment")
val ec2_networkInterfacePermission        = ec2("NetworkInterfacePermission")
val ec2_placementGroup                    = ec2("PlacementGroup")
val ec2_route                             = ec2("Route")
val ec2_routeTable                        = ec2("RouteTable")
val ec2_securityGroup                     = ec2("SecurityGroup")
val ec2_securityGroupEgress               = ec2("SecurityGroupEgress")
val ec2_securityGroupIngress              = ec2("SecurityGroupIngress")
val ec2_spotFleet                         = ec2("SpotFleet")
val ec2_subnet                            = ec2("Subnet")
val ec2_subnetCidrBlock                   = ec2("SubnetCidrBlock")
val ec2_subnetNetworkAclAssociation       = ec2("SubnetNetworkAclAssociation")
val ec2_subnetRouteTableAssociation       = ec2("SubnetRouteTableAssociation")
val ec2_volume                            = ec2("Volume")
val ec2_volumeAttachment                  = ec2("VolumeAttachment")
val ec2_vpc                               = ec2("VPC")
val ec2_vpcCidrBlock                      = ec2("VPCCidrBlock")
val ec2_vpcDhcpOptionsAssociation         = ec2("VPCDHCPOptionsAssociation")
val ec2_vpcEndpoint                       = ec2("VPCEndpoint")
val ec2_vpcEndpointConnectionNotification = ec2("VPCEndpointConnectionNotification")
val ec2_vpcEndpointService                = ec2("VPCEndpointService")
val ec2_vpcEndpointServicePermissions     = ec2("VPCEndpointServicePermissions")
val ec2_vpcGatewayAttachments             = ec2("VPCGatewayAttachments")
val ec2_vpcPeeringConnection              = ec2("VPCPeeringConnection")
val ec2_vpnConnection                     = ec2("VPNConnection")
val ec2_vpnConnectionRoute                = ec2("VPNConnectionRoute")
val ec2_vpnGateway                        = ec2("VPNGateway")
val ec2_vpnGatewayRoutePropagation        = ec2("VPNGatewayRoutePropagation")

