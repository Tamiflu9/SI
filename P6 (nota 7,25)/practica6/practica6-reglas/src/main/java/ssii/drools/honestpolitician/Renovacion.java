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

package ssii.drools.honestpolitician;

import org.kie.api.KieServices;
import org.kie.api.event.rule.DebugAgendaEventListener;
import org.kie.api.event.rule.DebugRuleRuntimeEventListener;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class Renovacion {

    /**
     * @param args
     */
    public static void main(final String[] args) {

        KieContainer kc = KieServices.Factory.get().getKieClasspathContainer();
        System.out.println(kc.verify().getMessages().toString());
        KieSession ksession = kc.newKieSession("HonestPoliticianKS");
        
     // The application can also setup listeners
        ksession.addEventListener( new DebugAgendaEventListener() );
        ksession.addEventListener( new DebugRuleRuntimeEventListener() );

        final Politician p1 = new Politician( "Politico no corrupto 1 ", true );
        final Politician p2 = new Politician( "Politico no corrupto 2 ", true );
        final Politician p3 = new Politician( "Politico no corrupto 3 ", true );
        final Politician p4 = new Politician( "Politico no corrupto 4 ", true );

        final Politician p5 = new Politician( "Politico corrupto 5 ", false );
        final Politician p6 = new Politician( "Politico corrupto 6 ", false );
        final Politician p7 = new Politician( "Politico corrupto 7 ", false );
        final Politician p8 = new Politician( "Politico corrupto 8 ", false );

        ksession.insert( p1 );
        ksession.insert( p2 );
        ksession.insert( p3 );
        ksession.insert( p4 );
        ksession.insert( p5 );
        ksession.insert( p6 );
        ksession.insert( p7 );
        ksession.insert( p8 );

        ksession.fireAllRules();

        ksession.dispose();
    }

}
