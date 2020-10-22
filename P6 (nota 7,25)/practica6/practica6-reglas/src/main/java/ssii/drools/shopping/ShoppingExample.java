/*
 * Copyright 2010 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package ssii.drools.shopping;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.kie.api.runtime.rule.FactHandle;

public class ShoppingExample {

    public static final void main(String[] args) {
        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        System.out.println(kc.verify().getMessages().toString());
        KieSession ksession = kc.newKieSession("ShoppingKS");

        Customer mark = new Customer( "mark",0 );
        ksession.insert( mark );

        Product shoes = new Product( "shoes", 60 );
        ksession.insert( shoes );

        Product hat = new Product( "hat", 60 );
        ksession.insert( hat );
        
        ksession.insert( new Purchase( mark, shoes ) );
        
// nuevo producto abrigo
        Product abrigo = new Product("abrigo", 100);
        ksession.insert(abrigo);

// cliente compra el abrigo
        ksession.insert(new Purchase(mark, abrigo));
                
        FactHandle hatPurchaseHandle = ksession.insert( new Purchase( mark, hat ) );
        imprimirMemoriaTrabajo(ksession);
        ksession.fireAllRules();    
        
        ksession.delete( hatPurchaseHandle );
        System.out.println( "Customer mark has returned the hat" );
        imprimirMemoriaTrabajo(ksession);
        ksession.fireAllRules();
        imprimirMemoriaTrabajo(ksession);
        
        
    }

	private static void imprimirMemoriaTrabajo(KieSession ksession) {
		System.out.println("--------BEGIN Working Memory-------------");
		for (Object obj:ksession.getObjects())
			System.out.println(obj);
		System.out.println("--------END Working Memory-------------");
	}

    public static class Customer {
        private String name;

        private int    discount;

        public Customer(String name,
                        int discount) {
            this.name = name;
            this.discount = discount;
        }

        public String getName() {
            return name;
        }

        public int getDiscount() {
            return discount;
        }

        public void setDiscount(int discount) {
            this.discount = discount;
        }
        public String toString(){
        	return "Customer( "+name+","+discount+")";
        }

    }

    public static class Discount {
        private Customer customer;
        private int      amount;

        public Discount(Customer customer,
                        int amount) {
            this.customer = customer;
            this.amount = amount;
        }

        public Customer getCustomer() {
            return customer;
        }

        public int getAmount() {
            return amount;
        }
        
        public String toString(){
        	return "Discount( "+customer+","+amount+")";
        }

    }

    public static class Product {
        private String name;
        private float  price;

        public Product(String name,
                       float price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public float getPrice() {
            return price;
        }
        
        public String toString(){
        	return "Product("+name+","+price+")";
        }

    }

    public static class Purchase {
        private Customer customer;
        private Product  product;

        public Purchase(Customer customer,
                        Product product) {
            this.customer = customer;
            this.product = product;
        }

        public Customer getCustomer() {
            return customer;
        }

        public Product getProduct() {
            return product;
        }
        
        public String toString(){
        	return "Purchase( "+customer+","+product+")";
        }
    }
}
