package com.yichuangzhihui.robotvrp.VRP;

import java.util.ArrayList;

/**
 * @Date :  2020/12/9 14:49
 * @Author : fanweihao
 * @Version: V1.0
 */
public class Vehicle {

    public int VehId;
    //创建一个节点的集合
    public ArrayList<Node> Route = new ArrayList<Node>();
    //容量
    public int capacity;
    //加载
    public int load;
    //当前位置
    public int CurLoc;
    //关闭
    public boolean Closed;

    public Vehicle(int id, int cap)
    {
        this.VehId = id;
        this.capacity = cap;
        this.load = 0;
        //默认在仓库
        this.CurLoc = 0; //In depot Initially

        this.Closed = false;
        this.Route.clear();
    }

    //将客户添加到车辆路线
    public void AddNode(Node Customer )//Add Customer to Vehicle Route
    {
        Route.add(Customer);
        this.load +=  Customer.demand;
        this.CurLoc = Customer.NodeId;
    }

    //检查是否有容量超标
    public boolean CheckIfFits(int dem) //Check if we have Capacity Violation
    {
        return ((load + dem <= capacity));
    }

    public int getVehId() {
        return VehId;
    }

    public void setVehId(int vehId) {
        VehId = vehId;
    }

    public ArrayList<Node> getRoute() {
        return Route;
    }

    public void setRoute(ArrayList<Node> route) {
        Route = route;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public int getLoad() {
        return load;
    }

    public void setLoad(int load) {
        this.load = load;
    }

    public int getCurLoc() {
        return CurLoc;
    }

    public void setCurLoc(int curLoc) {
        CurLoc = curLoc;
    }

    public boolean isClosed() {
        return Closed;
    }

    public void setClosed(boolean closed) {
        Closed = closed;
    }
}
