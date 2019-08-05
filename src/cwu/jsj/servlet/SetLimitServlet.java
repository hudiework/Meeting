package cwu.jsj.servlet;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import cwu.jsj.dao.FunctionDao;
import cwu.jsj.dao.RoleDao;
import cwu.jsj.dao.WorkerDao;
import cwu.jsj.entity.WorkerBean;

public class SetLimitServlet extends HttpServlet {
	public static int BUFFER_SIZE = 4096; // buffer_size
	private static final long serialVersionUID = 1L;

	public SetLimitServlet() {
		super();
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		/*
		 * get方法可以直接获取request的参数 但是要保证名字一致
		 */
		doPost(request, response);
		response.getWriter().append("Served at: ")
				.append(request.getContextPath());

	}

	/*
	 * post由于客户端以输出流的方法写出 所以要获取输入流 调用request.getInputSteam
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 返回字符串
		// String responseMsg="";
		// 输出流
		response.setCharacterEncoding("utf-8");
		PrintWriter out = response.getWriter();
		// 设置编码形式
		request.setCharacterEncoding("utf-8");
		// 获取传入数据
		String meetingId = request.getParameter("meetingId");
		if (meetingId == null || meetingId.isEmpty()) {
			out.print("未接收到客户端传来的数据！");
		} else {
			System.out.println("meetingId:" + meetingId + " --获取到终端数据！！！");

			// 访问数据库
			RoleDao roleDao = new RoleDao();
			FunctionDao functionDao = new FunctionDao();
			WorkerDao workerDao = new WorkerDao();
			List<Map<String, String>> mapList = new ArrayList<Map<String, String>>();// 存放由worker_function_mapping表查询到的所有数据
			List<WorkerBean> workerBeanList = new ArrayList<WorkerBean>();// 存放worker对象，用于传递给客户端
			List<String> functionList = new ArrayList<String>();// 一个员工的所有有效职能放进一个list中
			WorkerBean workerBean = new WorkerBean();
			String jsonStr = "";
			try {
				mapList = roleDao.queryAllWorFrMapping(meetingId);
				String workerId = mapList.get(0).get("workerId");// 初始，先查找第一个worker的workerId
				// 查找到这个worker的所有信息，并封装成workerBean但此时functionList属性还没赋值
				System.out.println("输出第一个工号：workerId：" + workerId);
				workerBean = workerDao.queryWorkerFrWorker(workerId);
				// 根据workerId查找到这个worker的所有有效职能
				functionList = functionDao.queryFunctionFrMapping(workerId);
				// 将functionList封装进该worker
				workerBean.setFunctionList(functionList);
				// 将该workerBean对象放到list中，用于传给客户端
				workerBeanList.add(workerBean);
				List<String> oldList = new ArrayList<String>();
				oldList.add(workerId);// 将已经封装过的workerId记录下来避免重复
				for (int i = 1; i < mapList.size(); i++) {
					workerId = mapList.get(i).get("workerId");
					System.out.println("遍历输出所有的工号："
							+ mapList.get(i).get("workerId"));
					for (int j = 0; j < oldList.size(); j++) {
						System.out.println("打印oldlist的长度：和workerId"
								+ oldList.size());
						if (workerId.equals(oldList.get(j))) {
							break;
						} else if (j == oldList.size() - 1) {
							// 若还是已经添加了的worker，则不执行添加操作，继续下一个;若是另一个worker则查找职能并封装进list
							oldList.add(workerId);
							workerBean = workerDao
									.queryWorkerFrWorker(workerId);
							functionList = functionDao
									.queryFunctionFrMapping(workerId);
							workerBean.setFunctionList(functionList);
							workerBeanList.add(workerBean);
							System.out.println("查找到工号为3的所有职能："
									+ functionList.toString());
						}

					}
				}
				System.out.println("打印输出list的大小，应该是2条数据："
						+ workerBeanList.size());
				jsonStr = JSONArray.fromObject(workerBeanList).toString();

			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("查询到的jsonArray打印输出：" + jsonStr);
			out.print(jsonStr);
		}
	}

	public static String inputStreamTOString(InputStream in) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] data = new byte[BUFFER_SIZE];
		int count = -1;
		while ((count = in.read(data, 0, BUFFER_SIZE)) != -1)
			outStream.write(data, 0, count);
		return new String(outStream.toByteArray());
	}

}
