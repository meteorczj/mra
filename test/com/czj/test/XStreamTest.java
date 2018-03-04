package com.czj.test;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.czj.entity.Entry;
import com.czj.entity.Item;
import com.czj.entity.Ufinterface;
import com.czj.entity.User;
import com.czj.entity.Voucher;
import com.czj.entity.VoucherBody;
import com.czj.entity.VoucherHead;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

public class XStreamTest {
	public static void main(String[] args) {
		// javabean 转 xml
		List<User> users = new ArrayList<User>();
		users.add(new User("123", "爱边程", "23"));
		users.add(new User("456", "刘大拿", "24"));
		BaseBean base = new BaseBean();
		base.setUserList(users);
		XStream xs = new XStream();
		xs.alias("root", BaseBean.class);
		xs.alias("user", User.class);
		xs.aliasField("list", BaseBean.class, "userList");
		String xml = xs.toXML(base);
		System.out.println("javabean转成xml为:\n" + xml);

		// xml转javabean
		XStream xs1 = new XStream(new DomDriver());
		xs1.alias("root", BaseBean.class);
		xs1.alias("user", User.class);
		xs1.aliasField("list", BaseBean.class, "userList");
		base = (BaseBean) xs1.fromXML(xml);
		users = base.getUserList();
		System.out.println("xml转成javabean为:");
		for (User u : users) {
			System.out.println("id=" + u.getId() + ",name=" + u.getName() + ",age=" + u.getAge());
		}
		
	}
	
	@Test
	public void voucherToXml() {
		//财务凭证生成xml
		Ufinterface uf = new Ufinterface();
		
		Voucher voucher = new Voucher();
		
		VoucherHead voucherHead = new VoucherHead();
		voucherHead.setCompany("test_com");
		
		VoucherBody voucherBody = new VoucherBody();
		List<Entry> entryList = new ArrayList<Entry>();
		Entry entry1 = new Entry();
		entry1.setAccount_code("111");
		entry1.setEntry_id("111");
		Entry entry2 = new Entry();
		entry2.setAccount_code("222");
		entry2.setEntry_id("222");
		
		List<Item> itemList = new ArrayList<Item>();
		Item item1 = new Item();
		item1.setName("客商辅助核算");
		item1.setValue("010001");
		Item item2 = new Item();
		item2.setName("22222");
		item2.setValue("22222");
		itemList.add(item1);
		itemList.add(item2);
		entry2.setItemList(itemList);
		
		entryList.add(entry1);
		entryList.add(entry2);
		voucherBody.setEntryList(entryList);
		
		voucher.setId("1008AA8129hhh505Krtr");
		voucher.setVoucherHead(voucherHead);
		voucher.setVoucherBody(voucherBody);
		
		uf.setRoottag("voucher");
		uf.setBilltype("gl");
		uf.setReplace("Y");
		uf.setReceiver("ce@ce-0001");
		uf.setVoucher(voucher);
		
		XStream xs = new XStream();
		xs.alias("ufinterface", Ufinterface.class);
		xs.alias("entry", Entry.class);
		xs.alias("item", Item.class);
		xs.aliasField("abstract", Entry.class, "remark");
		xs.aliasField("voucher_head", Voucher.class, "voucherHead");
		xs.aliasField("voucher_body", Voucher.class, "voucherBody");
		xs.aliasField("auxiliary_accounting", Entry.class, "itemList");
		xs.addImplicitCollection(VoucherBody.class, "entryList");//删除list节点
		xs.useAttributeFor(Item.class, "name");
		xs.useAttributeFor(Voucher.class, "id");//设置某个节点显示到父节点的属性中
		xs.useAttributeFor(Ufinterface.class, "roottag");
		xs.useAttributeFor(Ufinterface.class, "billtype");
		xs.useAttributeFor(Ufinterface.class, "replace");
		xs.useAttributeFor(Ufinterface.class, "receiver");
		xs.useAttributeFor(Ufinterface.class, "sender");
		xs.useAttributeFor(Ufinterface.class, "isexchange");
		xs.useAttributeFor(Ufinterface.class, "filename");
		xs.useAttributeFor(Ufinterface.class, "proc");
		xs.useAttributeFor(Ufinterface.class, "operation");
		
		StringBuffer xmlStr = new StringBuffer("<?xml version='1.0' encoding='gb2312'?>\r\n");
		String voucherStr = xs.toXML(uf);
		String voucherXml = xmlStr.append(voucherStr).toString();
		voucherXml = voucherXml.replace("<value>", "");
		voucherXml = voucherXml.replace("</value>", "");
		voucherXml = voucherXml.replace("__", "_");
		System.out.println("javabean转成xml为:\n" + voucherXml);
		
		try {
			FileWriter writer = new FileWriter("d:\\voucher.xml");
			writer.write(voucherXml);
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	

}
