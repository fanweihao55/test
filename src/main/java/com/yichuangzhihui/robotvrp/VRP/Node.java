package com.yichuangzhihui.robotvrp.VRP;

/**
 * @Date :  2020/12/9 14:47
 * @Author : fanweihao
 * @Version: V1.0
 */
public class Node {
    public int NodeId;
    public double Node_X, Node_Y; //Node Coordinates
    public int demand; //Node Demand if Customer
    public boolean IsRouted;
    private boolean IsDepot; //True if it Depot Node

    public Node(double depot_x, double depot_y) //Cunstructor for depot
    {
        this.NodeId = 0;
        this.Node_X = depot_x;
        this.Node_Y = depot_y;
        this.IsDepot = true;
    }

    public Node(int id, double x, double y, int demand) //Cunstructor for Customers
    {
        this.NodeId = id;
        this.Node_X = x;
        this.Node_Y = y;
        this.demand = demand;
        this.IsRouted = false;
        this.IsDepot = false;
    }

    public int getNodeId() {
        return NodeId;
    }

    public void setNodeId(int nodeId) {
        NodeId = nodeId;
    }

    public double getNode_X() {
        return Node_X;
    }

    public void setNode_X(double node_X) {
        Node_X = node_X;
    }

    public double getNode_Y() {
        return Node_Y;
    }

    public void setNode_Y(double node_Y) {
        Node_Y = node_Y;
    }

    public int getDemand() {
        return demand;
    }

    public void setDemand(int demand) {
        this.demand = demand;
    }

    public boolean isRouted() {
        return IsRouted;
    }

    public void setRouted(boolean routed) {
        IsRouted = routed;
    }

    public boolean isDepot() {
        return IsDepot;
    }

    public void setDepot(boolean depot) {
        IsDepot = depot;
    }
}
