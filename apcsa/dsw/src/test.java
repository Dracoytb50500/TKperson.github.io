class test {
	public static void main(String[] args) {
		try {
			Class<?> cls = Class.forName("fuck");
			Object fuc = cls.newInstance();

		} catch(ClassNotFoundException
		       |InstantiationException
		       |IllegalAccessException e) {

			System.out.println(e);

		}
	}
}

class fuck {
	public fuck(String... args) {
		System.out.println();
	}
}
