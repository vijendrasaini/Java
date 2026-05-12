JMM is basically:

A rulebook controlling:
- visibility
- ordering
- synchronization
- inter-thread communication

across all Java platforms consistently.



1. What Is Happens-Before?

Happens-before is a rule defined by JMM.

It specifies:
When one thread is GUARANTEED to see another thread’s actions.

Simplest Definition

If:
A happens-before B
then:
All changes made by A
are guaranteed visible to B.

AND:
A is observed before B.
So happens-before provides:

Guarantee	Meaning
Visibility	changes become visible
Ordering	execution order preserved


It is:
a visibility + ordering guarantee.


Deep Intuition

Think:
Happens-before creates a legal communication channel between threads.

Without it:
threads are not guaranteed to agree on memory state.


2. Happens-Before Is About OBSERVATION
VERY IMPORTANT.
It does NOT mean:
A physically executes before B
It means:
B is guaranteed to observe effects of A.


4. Happens-Before Rules (VERY IMPORTANT)

Rule 1 — Program Order Rule

Within SAME thread:

Earlier statements happen-before later statements.

Example:

a = 10;
b = 20;

Here:

a=10 happens-before b=20

This rule applies:

inside one thread only.

Other threads may still observe differently WITHOUT synchronization.

This connects directly to:

reordering
as-if-serial semantics


Rule 2 — Monitor Lock Rule

This is for:

synchronized

Rule:

unlock happens-before subsequent lock on same monitor.


Rule 3 — Volatile Variable Rule

Rule:

volatile write happens-before subsequent volatile read.
Important Insight

Volatile does NOT only synchronize itself.

It also flushes preceding writes.

VERY IMPORTANT.

Rule 4 — Thread Start Rule

Rule:

Actions before thread.start()
happen-before actions inside started thread.

Rule 5 — Thread Join Rule

Rule:

All actions inside thread
happen-before successful join().


Rule 6 — Transitivity Rule

This is extremely powerful.

If:

A happens-before B
B happens-before C

then:

A happens-before C

7. Deep Mental Model

Without happens-before:

threads may observe independent realities.

With happens-before:

Java establishes memory synchronization contract.

One Of The Deepest Concurrency Sentences
Concurrency correctness is fundamentally about establishing proper happens-before relationships.

This is the heart of JMM.