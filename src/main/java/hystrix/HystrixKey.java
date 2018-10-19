package hystrix;

public class HystrixKey {

	private HystrixKey() {
	}

	public static class Service {

		private Service() {
		}

		public static final String TASK_SERVICE = "task-service";
		public static final String TASK_REMINDER_SERVICE = "task-reminder-service";
		public static final String USER_SERVICE = "user-service";

	}

	public static class ThreadPool {

		private ThreadPool() {
		}

		public static final String TASK_SERVICE_THREADPOOL = "task-service";
		public static final String TASK_REMINDER_SERVICE_THREADPOOL = "task-reminder-service";
		public static final String USER_SERVICE_THREADPOOL = "user-service";
	}

	public static class Command {

		private Command() {
		}

		public static final String DELETE_A_TASK = "delete-a-task";
		public static final String FIND_A_TASK = "find-a-task";
		public static final String FIND_ALL_TASK = "find-all-tasks";
	}

}
