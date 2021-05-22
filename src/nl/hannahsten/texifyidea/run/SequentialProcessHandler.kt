package nl.hannahsten.texifyidea.run

import com.intellij.execution.KillableProcess
import com.intellij.execution.process.*
import java.io.OutputStream

/**
 * Execute the given list of processes sequentially.
 *
 * @author Sten Wessel
 */
class SequentialProcessHandler(private val processes: List<ProcessHandler>) : ProcessHandler(), KillableProcess {

    private var currentProcess: ProcessHandler? = null
    private var killed = false

    init {
        require(processes.isNotEmpty())

        processes.dropLast(1).withIndex().forEach { (i, p) ->
            p.addProcessListener(object : ProcessAdapter() {
                override fun processTerminated(event: ProcessEvent) {
                    if (killed) {
                        this@SequentialProcessHandler.notifyProcessTerminated(event.exitCode)
                    }
                    else {
                        currentProcess = processes[i+1]
                        currentProcess?.startNotify()
                    }
                }
            })
        }

        processes.last().addProcessListener(object : ProcessAdapter() {
            override fun processTerminated(event: ProcessEvent) {
                this@SequentialProcessHandler.notifyProcessTerminated(event.exitCode)
            }
        })

    }

    override fun startNotify() {
        super.startNotify()
        currentProcess = processes.firstOrNull()
        currentProcess?.startNotify()
    }

    override fun destroyProcessImpl() {
        currentProcess?.destroyProcess()
    }

    override fun detachProcessImpl() {
        currentProcess?.detachProcess()
    }

    override fun detachIsDefault(): Boolean {
        return false
    }

    override fun getProcessInput(): OutputStream? {
        return null
    }

    override fun canKillProcess() = (currentProcess as? KillableProcess?)?.canKillProcess() ?: false

    override fun killProcess() {
        killed = true
        (currentProcess as? KillableProcess)?.killProcess()
    }
}

interface StepExecutionHandler {

    fun startNotify()

    fun killProcess()

    fun canKillProcess(): Boolean

    fun destroyProcess()

    fun detachProcess()

    fun addProcessListener(listener: ProcessListener)
}