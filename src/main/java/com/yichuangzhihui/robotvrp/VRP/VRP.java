package com.yichuangzhihui.robotvrp.VRP;
import java.util.ArrayList;
import java.util.List;

/**
 * @Date :  2020/12/9 14:54
 * @Author : fanweihao
 * @Version: V1.0
 */
public class VRP {

    //private static double EARTH_RADIUS = 6371393;

    //noOfCustomers 目标 noOfVehicles无人机 nodes节点
    public  List<String> VrpSolution (int noOfCustomers, int noOfVehicles, Node[] nodes){

        List<String> list=new ArrayList<>();
        int VehicleCap = 80; // 负载(无用)
        //Tabu Parameter
        // 邻域 (有用，不用调)
        int TABU_Horizon = 10;
        // 定义距离矩阵
        double[][] distanceMatrix = new double[noOfCustomers + 1][noOfCustomers + 1];
        double Delta_x, Delta_y;
        // 双路循环算法，找出每一个任务点之间的距离
        for (int i = 0; i <= noOfCustomers; i++) {
            for (int j = i + 1; j <= noOfCustomers; j++) //The table is summetric to the first diagonal
            {                                      //Use this to compute distances in O(n/2)

                Delta_x = (nodes[i].Node_X - nodes[j].Node_X);
                Delta_y = (nodes[i].Node_Y - nodes[j].Node_Y);
                double distance = Math.sqrt((Delta_x * Delta_x) + (Delta_y * Delta_y));

                distance = Math.round(distance);                //Distance is Casted in Integer
                //distance = Math.round(distance*100.0)/100.0; //Distance in double

                distanceMatrix[i][j] = distance;
                distanceMatrix[j][i] = distance;
            }
        }
        //Compute the greedy Solution
        // 贪心算法 打印
        //System.out.println("Attempting to resolve Vehicle Routing Problem (VRP) for " + NoOfCustomers +
        //        " Customers and " + noOfVehicles + " Vehicles" + " with " + VehicleCap + " units of capacity\n");
        // 贪心算法 调用--------------------开始
        // 参数1: 客户数量
        // 参数2: 无人机数量n
        // 参数3: 载荷

        Solution s = new Solution(noOfCustomers, noOfVehicles, VehicleCap);
        // 执行贪心方法 GreedySolution 装载参数
        // 参数1: 任务点坐标
        // 参数2: 距离矩阵
         s.GreedySolution(nodes, distanceMatrix);

        // 打印 贪心算法 结果
        String greedy_solution = s.SolutionPrint("Greedy Solution");

        if (greedy_solution!= null||!greedy_solution.equals("")){
            list.add(greedy_solution);
        }
        // 生成贪心算法 图片
        //draw.drawRoutes(s, "Greedy_Solution");
        // 邻域 搜索 算法
        s.IntraRouteLocalSearch(nodes, distanceMatrix);
        // 邻域 搜索 打印
        String solutionPrint = s.SolutionPrint("Solution after Intra-Route Heuristic Neighborhood Search");
        if (solutionPrint!=null||!solutionPrint.equals("")){
            list.add(solutionPrint);
        }
        // 邻域 搜索 画图
        //draw.drawRoutes(s, "Intra-Route");

      /*  s.GreedySolution(nodes, distanceMatrix);

        s.InterRouteLocalSearch(nodes, distanceMatrix);

        String solutionPrint1 = s.SolutionPrint("Solution after Inter-Route Heuristic Neighborhood Search");

        if (solutionPrint1!=null||!solutionPrint1.equals("")){
            list.add(solutionPrint1);
        }*/
        //draw.drawRoutes(s, "Inter-Route");

        // 贪心算法 最优解 输出
        s.GreedySolution(nodes, distanceMatrix);
        // 调用 禁忌 算法 (主要用于学习过程中最优的提升)
        s.TabuSearch(TABU_Horizon, distanceMatrix);
        // 输出
        String solution_after_tabu_search = s.SolutionPrint("Solution After Tabu Search");
        if (solution_after_tabu_search!=null||!solution_after_tabu_search.equals("")){
            list.add(solution_after_tabu_search);
        }
        // 画图
        //.drawRoutes(s, "TABU_Solution");

        return list;

    }

   //private static double rad(double d) {
   //    return d * Math.PI / 180.0;
   //}


}
