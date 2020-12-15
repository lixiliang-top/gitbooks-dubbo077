package cn.kgc.booksconsumer;

import cn.kgc.pojo.Entry;
import cn.kgc.pojo.MyPageInfo;
import cn.kgc.service.EntryService;
import org.apache.dubbo.config.annotation.Reference;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BooksConsumerApplicationTests {

	@Reference
	EntryService entryService;

	@Test
	void contextLoads() {
		MyPageInfo<Entry> entryMyPageInfo = entryService.selectBycategoryid(0, 1, 2);
		for (Entry entry : entryMyPageInfo.getList()) {
			System.out.println(entry.toString());
		}
	}

}
