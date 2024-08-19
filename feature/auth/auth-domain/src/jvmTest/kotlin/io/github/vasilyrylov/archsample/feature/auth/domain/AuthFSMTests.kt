package io.github.vasilyrylov.archsample.feature.auth.domain

import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.AuthFSMState
import io.github.vasilyrylov.archsample.feature.auth.domain.fsm.actions.AuthFSMAction
import junit.framework.TestCase.assertTrue
import ru.kontur.mobile.visualfsm.tools.VisualFSM
import ru.kontur.mobile.visualfsm.tools.graphviz.preset.DotAttributesDefaultPreset
import kotlin.test.Test

class AuthFSMTests {
    @Test
    fun generateDigraph() {
        println(
            VisualFSM.generateDigraph(
                baseAction = AuthFSMAction::class,
                baseState = AuthFSMState::class,
                initialState = AuthFSMState.Login::class,
                attributes = DotAttributesDefaultPreset(AuthFSMState.AsyncWorkState::class)
            )
        )
        assertTrue(true)
    }

    @Test
    fun allStatesReachableTest() {
        val notReachableStates = VisualFSM.getUnreachableStates(
            baseAction = AuthFSMAction::class,
            baseState = AuthFSMState::class,
            initialState = AuthFSMState.Login::class,
        )

        assertTrue(
            "FSM have unreachable states: ${notReachableStates.joinToString(", ")}",
            notReachableStates.isEmpty()
        )
    }

    @Test
    fun noFinalStateTest() {
        val finalStates = VisualFSM.getFinalStates(
            baseAction = AuthFSMAction::class,
            baseState = AuthFSMState::class,
        )

        assertTrue(
            "FSM have not correct final states: ${finalStates.joinToString(", ")}",
            finalStates.isEmpty()
        )
    }
}